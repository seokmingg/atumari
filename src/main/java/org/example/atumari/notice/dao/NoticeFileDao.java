package org.example.atumari.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.notice.dto.NoticeFileDto;

public class NoticeFileDao {

    public int insertFile(int noticeNo, String originalFileName, String storedFileName) {
        String sql = "INSERT INTO notice_file "
                + "(notice_no, original_file_name, stored_file_name) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noticeNo);
            ps.setString(2, originalFileName);
            ps.setString(3, storedFileName);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 첨부파일 정보 저장에 실패했습니다.", e);
        }
    }

    public List<NoticeFileDto> findByNoticeNo(int noticeNo) {
        String sql = "SELECT file_no, notice_no, original_file_name, stored_file_name "
                + "FROM notice_file WHERE notice_no = ? ORDER BY file_no";
        List<NoticeFileDto> files = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, noticeNo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    files.add(mapFile(rs));
                }
            }
            return files;
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 첨부파일 조회에 실패했습니다.", e);
        }
    }

    public NoticeFileDto findById(int fileNo) {
        String sql = "SELECT file_no, notice_no, original_file_name, stored_file_name "
                + "FROM notice_file WHERE file_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fileNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapFile(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("공지사항 첨부파일 조회에 실패했습니다.", e);
        }
    }

    private NoticeFileDto mapFile(ResultSet rs) throws SQLException {
        return new NoticeFileDto(
                rs.getInt("file_no"),
                rs.getInt("notice_no"),
                rs.getString("original_file_name"),
                rs.getString("stored_file_name")
        );
    }
}
