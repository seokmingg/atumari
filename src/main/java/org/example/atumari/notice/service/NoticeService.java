package org.example.atumari.notice.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.example.atumari.common.storage.S3Storage;
import org.example.atumari.notice.dao.NoticeDao;
import org.example.atumari.notice.dao.NoticeFileDao;
import org.example.atumari.notice.dto.NoticeDto;
import org.example.atumari.notice.dto.NoticeFileDto;
import org.example.atumari.notice.dto.NoticeListPageDto;

import jakarta.servlet.http.Part;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class NoticeService {

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_GROUP_SIZE = 5;
    private static final int MAX_FILE_COUNT = 3;
    private static final long MAX_FILE_SIZE = 10L * 1024 * 1024;
    private static final List<String> ALLOWED_EXTENSIONS = List.of(
            "jpg", "jpeg", "png", "pdf", "doc", "docx", "xls", "xlsx"
    );

    private final NoticeDao noticeDao = new NoticeDao();
    private final NoticeFileDao noticeFileDao = new NoticeFileDao();

    public NoticeListPageDto getNoticePage(
            int requestedPage, String searchType, String keyword) {
        String normalizedSearchType = normalizeSearchType(searchType);
        String normalizedKeyword = keyword == null ? "" : keyword.trim();
        int totalCount = noticeDao.countNotices(
                normalizedSearchType, normalizedKeyword);
        int totalPage = getTotalPage(totalCount);
        int currentPage = Math.min(Math.max(1, requestedPage), totalPage);
        int startPage = getStartPage(currentPage);
        int endPage = getEndPage(startPage, totalPage);
        int offset = (currentPage - 1) * PAGE_SIZE;

        List<NoticeDto> noticeList = noticeDao.findNotices(
                PAGE_SIZE,
                offset,
                normalizedSearchType,
                normalizedKeyword
        );

        return new NoticeListPageDto(
                noticeList,
                currentPage,
                PAGE_SIZE,
                totalCount,
                totalPage,
                startPage,
                endPage,
                normalizedSearchType,
                normalizedKeyword
        );
    }

    public NoticeDto getNotice(int noticeNo) {
        return noticeDao.findById(noticeNo);
    }

    public void createNotice(
            String title, String content, String authorEmail, List<Part> files) {
        String normalizedTitle = validateTitle(title);
        String normalizedContent = validateContent(content);
        if (authorEmail == null || authorEmail.isBlank()) {
            throw new IllegalArgumentException("ログイン情報を確認してください。");
        }
        validateFiles(files);

        int noticeNo = noticeDao.insertNotice(normalizedTitle, normalizedContent, authorEmail);
        if (noticeNo <= 0) {
            throw new IllegalArgumentException("管理者会員情報を確認してください。");
        }

        try {
            saveFiles(files, noticeNo);
        } catch (RuntimeException e) {
            noticeDao.deleteNotice(noticeNo);
            throw e;
        }
    }

    public void updateNotice(int noticeNo, String title, String content) {
        String normalizedTitle = validateTitle(title);
        String normalizedContent = validateContent(content);

        if (noticeDao.updateNotice(noticeNo, normalizedTitle, normalizedContent) != 1) {
            throw new IllegalArgumentException("お知らせが見つかりません。");
        }
    }

    public void deleteNotice(int noticeNo) {
        List<NoticeFileDto> files = noticeFileDao.findByNoticeNo(noticeNo);
        if (noticeDao.deleteNotice(noticeNo) != 1) {
            throw new IllegalArgumentException("お知らせが見つかりません。");
        }
        for (NoticeFileDto file : files) {
            S3Storage.delete(file.getStoredFileName());
        }
    }

    public List<NoticeFileDto> getNoticeFiles(int noticeNo) {
        return noticeFileDao.findByNoticeNo(noticeNo);
    }

    public NoticeFileDto getNoticeFile(int fileNo) {
        return noticeFileDao.findById(fileNo);
    }

    public ResponseBytes<GetObjectResponse> downloadNoticeFile(NoticeFileDto file) {
        return S3Storage.download(file.getStoredFileName());
    }

    private void saveFiles(List<Part> files, int noticeNo) {
        List<String> uploadedKeys = new ArrayList<>();
        try {
            for (Part file : files) {
                String originalFileName = sanitizeFileName(file.getSubmittedFileName());
                String objectKey = "notice/" + noticeNo + "/"
                        + UUID.randomUUID() + "_" + originalFileName;

                try (InputStream inputStream = file.getInputStream()) {
                    S3Storage.upload(
                            objectKey,
                            inputStream,
                            file.getSize(),
                            file.getContentType()
                    );
                }
                uploadedKeys.add(objectKey);

                if (noticeFileDao.insertFile(noticeNo, originalFileName, objectKey) != 1) {
                    throw new RuntimeException("공지사항 첨부파일 정보 저장에 실패했습니다.");
                }
            }
        } catch (IOException | RuntimeException e) {
            for (String objectKey : uploadedKeys) {
                try {
                    S3Storage.delete(objectKey);
                } catch (RuntimeException ignored) {
                    // 원래 업로드 실패 원인을 유지합니다.
                }
            }
            throw new RuntimeException("공지사항 첨부파일 저장에 실패했습니다.", e);
        }
    }

    private void validateFiles(List<Part> files) {
        if (files.size() > MAX_FILE_COUNT) {
            throw new IllegalArgumentException("添付ファイルは3個まで登録できます。");
        }

        for (Part file : files) {
            String fileName = sanitizeFileName(file.getSubmittedFileName());
            if (file.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("1ファイルあたりのサイズは10MB以下にしてください。");
            }
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex < 0 || !ALLOWED_EXTENSIONS.contains(
                    fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT))) {
                throw new IllegalArgumentException("許可されていないファイル形式です。");
            }
        }
    }

    private String sanitizeFileName(String submittedFileName) {
        if (submittedFileName == null || submittedFileName.isBlank()) {
            throw new IllegalArgumentException("ファイル名が正しくありません。");
        }
        String normalizedName = submittedFileName.replace('\\', '/');
        String fileName = Path.of(normalizedName).getFileName().toString();
        return fileName.replaceAll("[\\r\\n]", "_");
    }

    private int getTotalPage(int totalCount) {
        return Math.max(1, (int) Math.ceil((double) totalCount / PAGE_SIZE));
    }

    private int getStartPage(int currentPage) {
        return ((currentPage - 1) / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE + 1;
    }

    private int getEndPage(int startPage, int totalPage) {
        return Math.min(startPage + PAGE_GROUP_SIZE - 1, totalPage);
    }

    private String normalizeSearchType(String searchType) {
        return "content".equals(searchType) ? "content" : "title";
    }

    private String validateTitle(String title) {
        String normalizedTitle = title == null ? "" : title.trim();

        if (normalizedTitle.isEmpty()) {
            throw new IllegalArgumentException("タイトルを入力してください。");
        }
        if (normalizedTitle.length() > 200) {
            throw new IllegalArgumentException("タイトルは200文字以下で入力してください。");
        }
        return normalizedTitle;
    }

    private String validateContent(String content) {
        String normalizedContent = content == null ? "" : content.trim();

        if (normalizedContent.isEmpty()) {
            throw new IllegalArgumentException("内容を入力してください。");
        }
        return normalizedContent;
    }
}
