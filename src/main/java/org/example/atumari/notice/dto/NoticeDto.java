package org.example.atumari.notice.dto;

import java.sql.Timestamp;

public class NoticeDto {
    private Integer noticeNo;
    private String title;
    private String content;
    private Long authorId;
    private String authorName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Integer getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(Integer noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSummary() {
        if (content == null) {
            return "";
        }

        String plainText = content.replaceAll("<[^>]*>", " ")
                .replaceAll("\\s+", " ")
                .trim();

        return plainText.length() > 80
                ? plainText.substring(0, 80) + "..."
                : plainText;
    }
}
