package org.example.atumari.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.notice.dto.NoticeDto;

public class NoticeDao {

    public int countNotices(String searchType, String keyword) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM notice");
        boolean hasKeyword = keyword != null && !keyword.isBlank();

        if (hasKeyword) {
            sql.append(" WHERE ")
               .append("content".equals(searchType) ? "content" : "title")
               .append(" LIKE ?");
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            if (hasKeyword) {
                ps.setString(1, "%" + keyword.trim() + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 개수 조회에 실패했습니다.", e);
        }
    }

    public List<NoticeDto> findNotices(
            int limit, int offset, String searchType, String keyword) {

        StringBuilder sql = new StringBuilder(
                "SELECT n.notice_no, n.title, n.content, n.author_id, " +
                "m.name AS author_name, n.created_at, n.updated_at " +
                "FROM notice n JOIN member m ON n.author_id = m.id");

        boolean hasKeyword = keyword != null && !keyword.isBlank();

        if (hasKeyword) {
            sql.append(" WHERE n.")
               .append("content".equals(searchType) ? "content" : "title")
               .append(" LIKE ?");
        }

        sql.append(" ORDER BY n.notice_no DESC LIMIT ? OFFSET ?");

        List<NoticeDto> notices = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;
            if (hasKeyword) {
                ps.setString(index++, "%" + keyword.trim() + "%");
            }
            ps.setInt(index++, limit);
            ps.setInt(index, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    notices.add(mapNotice(rs));
                }
            }

            return notices;
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 목록 조회에 실패했습니다.", e);
        }
    }

    public NoticeDto findById(int noticeNo) {
        String sql =
                "SELECT n.notice_no, n.title, n.content, n.author_id, " +
                "m.name AS author_name, n.created_at, n.updated_at " +
                "FROM notice n JOIN member m ON n.author_id = m.id " +
                "WHERE n.notice_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, noticeNo);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapNotice(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 상세 조회에 실패했습니다.", e);
        }
    }

    public int insertNotice(String title, String content, String authorEmail) {
        String sql =
                "INSERT INTO notice (title, content, author_id) " +
                "SELECT ?, ?, id FROM member WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, authorEmail);
            if (ps.executeUpdate() != 1) {
                return 0;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 등록에 실패했습니다.", e);
        }
    }

    public int updateNotice(int noticeNo, String title, String content) {
        String sql =
                "UPDATE notice " +
                "SET title = ?, content = ?, updated_at = CURRENT_TIMESTAMP " +
                "WHERE notice_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, noticeNo);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 수정에 실패했습니다.", e);
        }
    }

    public int deleteNotice(int noticeNo) {
        String sql = "DELETE FROM notice WHERE notice_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noticeNo);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 삭제에 실패했습니다.", e);
        }
    }

    private NoticeDto mapNotice(ResultSet rs) throws SQLException {
        NoticeDto notice = new NoticeDto();
        notice.setNoticeNo(rs.getInt("notice_no"));
        notice.setTitle(rs.getString("title"));
        notice.setContent(rs.getString("content"));
        notice.setAuthorId(rs.getLong("author_id"));
        notice.setAuthorName(rs.getString("author_name"));
        notice.setCreatedAt(rs.getTimestamp("created_at"));
        notice.setUpdatedAt(rs.getTimestamp("updated_at"));
        return notice;
    }
}
