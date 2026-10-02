package org.example.atumari.festival.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;
import org.example.atumari.festival.dto.ReviewDto;

public class ReviewDao {

	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
	
	//리뷰 리스트
    public List<ReviewDto> getReviewList(int festivalNo, int page, int pageSize) {

        List<ReviewDto> reviewList = new ArrayList<>();

        int offset = (page - 1) * pageSize;

        String sql = """
                SELECT
                    r.review_no,
                    r.festival_no,
                    r.member_id,
                    m.name,
                    r.content,
                    r.created_date,
                    r.updated_date
                FROM festival_review r
                JOIN member m
                    ON r.member_id = m.id
                WHERE r.festival_no = ?
                ORDER BY r.created_date DESC, r.review_no DESC
                LIMIT ? OFFSET ?
                """;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            pstmt.setInt(1, festivalNo);
            pstmt.setInt(2, pageSize);
            pstmt.setInt(3, offset);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    ReviewDto dto = new ReviewDto();

                    dto.setReview_no(rs.getLong("review_no"));
                    dto.setFestival_no(rs.getInt("festival_no"));
                    dto.setMember_id(rs.getLong("member_id"));
                    dto.setName(rs.getString("name"));
                    dto.setContent(rs.getString("content"));

                    if (rs.getTimestamp("created_date") != null) {
                        dto.setCreated_date(
                            rs.getTimestamp("created_date").toLocalDateTime()
                        );
                    }

                    if (rs.getTimestamp("updated_date") != null) {
                        dto.setUpdated_date(
                            rs.getTimestamp("updated_date").toLocalDateTime()
                        );
                    }

                    reviewList.add(dto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviewList;
    }
    

    
    // 리뷰 작성
    public boolean writeReview(ReviewDto review) {

        String sql = """
                INSERT INTO festival_review (
                    festival_no,
                    member_id,
                    content
                )
                VALUES (?, ?, ?)
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, review.getFestival_no());
            pstmt.setLong(2, review.getMember_id());
            pstmt.setString(3, review.getContent());

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
    
    
    // 리뷰 수정
    public boolean updateReview(ReviewDto review) {

        String sql = """
                UPDATE festival_review
                SET content = ?,
                    updated_date = CURRENT_TIMESTAMP
                WHERE review_no = ?
                AND member_id = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, review.getContent());
            pstmt.setLong(2, review.getReview_no());
            pstmt.setLong(3, review.getMember_id());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
    
    //삭제
    public boolean deleteReview(ReviewDto review) {

        String sql = """
                DELETE FROM festival_review
                WHERE review_no = ?
                AND member_id = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setLong(1, review.getReview_no());
            pstmt.setLong(2, review.getMember_id());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
    
    
    // 리뷰 전체 개수
    public int getReviewCount(int festivalNo) {

        String sql = """
                SELECT COUNT(*)
                FROM festival_review
                WHERE festival_no = ?
                """;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {

            pstmt.setInt(1, festivalNo);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    // 관리자 리뷰 수정
    public boolean updateReviewByAdmin(ReviewDto review) {

        String sql = """
                UPDATE festival_review
                SET content = ?,
                    updated_date = CURRENT_TIMESTAMP
                WHERE review_no = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, review.getContent());
            pstmt.setLong(2, review.getReview_no());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 관리자 리뷰 삭제
    public boolean deleteReviewByAdmin(ReviewDto review) {

        String sql = """
                DELETE FROM festival_review
                WHERE review_no = ?
                """;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setLong(1, review.getReview_no());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
