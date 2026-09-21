package org.example.atumari.notice.dto;

public class NoticeFileDto {

    private final int fileNo;
    private final int noticeNo;
    private final String originalFileName;
    private final String storedFileName;

    public NoticeFileDto(int fileNo, int noticeNo, String originalFileName, String storedFileName) {
        this.fileNo = fileNo;
        this.noticeNo = noticeNo;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
    }

    public int getFileNo() {
        return fileNo;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }
}
