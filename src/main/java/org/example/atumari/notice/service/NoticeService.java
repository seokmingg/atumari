package org.example.atumari.notice.service;

import java.util.List;

import org.example.atumari.notice.dao.NoticeDao;
import org.example.atumari.notice.dto.NoticeDto;
import org.example.atumari.notice.dto.NoticeListPageDto;

public class NoticeService {

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_GROUP_SIZE = 5;

    private final NoticeDao noticeDao = new NoticeDao();

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

    public void createNotice(String title, String content, String authorEmail) {
        String normalizedTitle = validateTitle(title);
        String normalizedContent = validateContent(content);
        if (authorEmail == null || authorEmail.isBlank()) {
            throw new IllegalArgumentException("ログイン情報を確認してください。");
        }
        if (noticeDao.insertNotice(normalizedTitle, normalizedContent, authorEmail) != 1) {
            throw new IllegalArgumentException("管理者会員情報を確認してください。");
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
        if (noticeDao.deleteNotice(noticeNo) != 1) {
            throw new IllegalArgumentException("お知らせが見つかりません。");
        }
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
