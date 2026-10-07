package org.example.atumari.inquiry.dao;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import org.example.atumari.common.database.DBConnection;

public class InquiryDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 문의 글 등록
	public int insertInquiry(InquiryDto inquiry) {
		
		int inquiry_no=0;
		
		String sql ="INSERT INTO inquiry " +
			    "(member_id, title, writer, is_public, content) " +
			    "VALUES (?, ?, ?, ?, ?)";
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//두번째 인수 의미:INSERT에서 DB가 자동 생성한 키도 나한테 돌려줘라는 의미
				ps.setLong(1, inquiry.getMember_id());
				ps.setString(2, inquiry.getTitle());
				ps.setString(3, inquiry.getWriter());
				ps.setBoolean(4, inquiry.isPublic());
				ps.setString(5, inquiry.getContent());
				
			int result = ps.executeUpdate();
			
			if(result > 0) {
				rs = ps.getGeneratedKeys();//JDBC에는 INSERT하면 DB가 자동 생성한 PK를 바로 받을 수 있는 기능
				/*SELECT MAX(inquiry_no) 같은 방식으로 다시 조회하는 건 
				 * 여러 사용자가 동시에 글을 작성하면 잘못된 번호를 가져올 위험이 있으므로 사용하지 않는 게 좋음*/
				
				if(rs.next()) {
					inquiry_no = rs.getInt(1);
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
				
		return inquiry_no;
	}
	
	
	// 전체 문의글 목록 조회
	public List<InquiryDto> findInquiryList(String searchType, String keyword, int pageSize,int offset, String status){
		List<InquiryDto> inquiryList = new ArrayList<>();
		
		// StringBuilder로 생성 -> 이유: sql.append 기능(sql문 덧붙이기)을 쓰기위해
		StringBuilder sql = new StringBuilder("""
				 SELECT i.inquiry_no,
			           i.title,
			           i.writer,
			           i.is_public,
			           i.status,
			           i.created_at,
		           EXISTS (
		               SELECT 1
		               FROM inquiry_file f
		               WHERE f.inquiry_no = i.inquiry_no
		           ) AS file_is
				  FROM inquiry i
				  where 1=1
				""");
		//where 1=1 은 항상 참인 조건. 이렇게 하면 검색어와 상태유무에 관계없이 추가 조건 and로 연결 가능
		
		// 검색어와 올바른 검색 조건이 있는지 확인
		boolean hasSearch = keyword != null && 
								!keyword.isBlank() &&
									("title".equals(searchType) || "writer".equals(searchType));
		
		// 답변상태가 조건에 맞게 옳게 값이 들어있는지
		boolean hasStatus = 
				"WAITING".equals(status) || "COMPLETED".equals(status);
		
		// 검색 조건만 동적으로 추가
		if(hasSearch) {
			if("title".equals(searchType)) {
				sql.append(" and i.title like ? ");//주의: 앞뒤 공백 주기, 앞뒷문장과 붙으면 안됨
			}else if("writer".equals(searchType)) {
				sql.append(" and i.writer like ? ");
			}
		}
		
		// 답변상태 조건 추가
		if(hasStatus) {
			sql.append(" AND i.status =? ");
		}
		
		//공통적으로 필요한 부분(페이지네이션)
		sql.append(" ORDER BY i.created_at DESC ");
		sql.append(" LIMIT ? OFFSET ?");//LIMIT 몇개의 행을 가져올 것인가, OFFSET 앞에서 몇개의 행을 건너뛸것 인가
				
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			
			int parameterIndex =1;// 만든이유는 검색어가 있을 때 없을 때 바인딩할 인덱스가 달라지기에 
			
			// 검색어 있을 때만 바인딩에 추가
				if(hasSearch) {
					ps.setString(parameterIndex++, "%"+keyword+"%");
				}
			// 답변상태가 있을때만 바인딩에 추가
				if(hasStatus) {
					ps.setString(parameterIndex++, status);
				}
			// 검색어 관계없이 항상 바인딩
			ps.setInt(parameterIndex++, pageSize);
			ps.setInt(parameterIndex, offset);
				
			rs = ps.executeQuery();
				
		
			while(rs.next()) {
				InquiryDto inquiryDto = new InquiryDto();
				
				inquiryDto.setInquiry_no(rs.getInt("inquiry_no"));
				inquiryDto.setTitle(rs.getString("title"));
				inquiryDto.setWriter(rs.getString("writer"));
				inquiryDto.setPublic(rs.getBoolean("is_public"));
		//		inquiryDto.setEmail(rs.getString("email"));
				inquiryDto.setStatus(rs.getString("status"));
				inquiryDto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime()); //InquiryDto에 타입으로 형변환
				inquiryDto.setFileIs(rs.getBoolean("file_is"));

	            inquiryList.add(inquiryDto);
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return inquiryList;
	}
	
	
	// 전체 문의글 갯수 조회
	public int getTotalInquiryCount(String searchType, String keyword,String status) {
		
		int totalCount =0;
		
		StringBuilder sql = new StringBuilder("""
				select count(*) as count
				from inquiry
				where 1=1
				""");
		
		// 검색어와 올바른 검색 조건이 있는지 확인
		boolean hasSearch = keyword != null && 
								!keyword.isBlank() &&
									("title".equals(searchType) || "writer".equals(searchType));
		
		// 답변상태가 조건에 맞게 옳게 값이 들어있는지
				boolean hasStatus = 
						"WAITING".equals(status) || "COMPLETED".equals(status);
				
				// 검색 조건만 동적으로 추가
				if(hasSearch) {
					if("title".equals(searchType)) {
						sql.append(" and title like ? ");//주의: 앞뒤 공백 주기, 앞뒷문장과 붙으면 안됨
					}else if("writer".equals(searchType)) {
						sql.append(" and writer like ? ");
					}
				}
				
				// 답변상태 조건 추가
				if(hasStatus) {
					sql.append(" AND status =? ");
				}
		
		try{
			con = DBConnection.getConnection();
			 ps = con.prepareStatement(sql.toString());

			 int parameterIndex = 1;

			 if (hasSearch) {
			     ps.setString(parameterIndex++, "%" + keyword + "%");
			 }

			 if (hasStatus) {
			     ps.setString(parameterIndex++, status);
			 }
			 
			rs = ps.executeQuery();
				
			if(rs.next()) {
				totalCount = rs.getInt("count");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return totalCount;
	}

	
	//문의 상세글 조회
	public InquiryDto getInquiry(int inquiryNo) {
		
		InquiryDto inquiryDto = null;
		
		String sql ="SELECT\r\n"
				+ "    i.inquiry_no,\r\n"
				+ "    i.member_id,\r\n"
				+ "    i.title,\r\n"
				+ "    i.writer,\r\n"
				+ "    i.status,\r\n"
				+ "    i.created_at,\r\n"
				+ "    i.is_public,\r\n"
				+ "    i.content,\r\n"
				+ "    i.answer_content,\r\n"
				+ "    i.answered_at\r\n"
				+ "FROM inquiry i\r\n"
				+ "\r\n"
				+ "WHERE i.inquiry_no = ?";
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
				ps.setInt(1, inquiryNo);
			rs = ps.executeQuery();
			
				if(rs.next()) {
					if(inquiryDto == null) {
						inquiryDto = new InquiryDto();
						
						inquiryDto.setInquiry_no(rs.getInt("inquiry_no"));
						inquiryDto.setMember_id(rs.getLong("member_id"));
						inquiryDto.setTitle(rs.getString("title"));
						inquiryDto.setWriter(rs.getString("writer"));
						inquiryDto.setStatus(rs.getString("status"));
						inquiryDto.setAnswer_content(rs.getString("answer_content"));
						Timestamp answeredAt = rs.getTimestamp("answered_at");
						// DB의 답변 시간을 DTO의 LocalDateTime 타입에 맞춰 변환

						if (answeredAt != null) {
						    inquiryDto.setAnswered_at(answeredAt.toLocalDateTime());
						} // 검증을 안하면 미답변일 때 null.toLocalDateTime()을 호출하게 되어 NullPointerException이 발생
						inquiryDto.setCreated_at(
						        rs.getTimestamp("created_at").toLocalDateTime()
						);
						inquiryDto.setPublic(rs.getBoolean("is_public"));
						inquiryDto.setContent(rs.getString("content"));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return inquiryDto;
	}


	// 문의글 수정
	public int updateInquiry(InquiryDto inquiry) {
		
		int result = 0;
		
		String sql ="UPDATE inquiry\r\n"
				+ "SET title = ?,\r\n"
				+ "    content = ?,\r\n"
				+ "    is_public = ?\r\n"
				+ "WHERE inquiry_no = ?\r\n"
				+ "  AND member_id = ?";
		// member_id를 조건으로 넣음으로서 본인이 아닌 경우는 수정이 불가하도록 한번더 확인
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
				ps.setString(1, inquiry.getTitle());
				ps.setString(2, inquiry.getContent());
				ps.setBoolean(3, inquiry.isPublic());
				ps.setInt(4, inquiry.getInquiry_no());
				ps.setLong(5, inquiry.getMember_id());
				
			result = ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	
	// 문의글 존재 여부 + 작성자 본인 확인
	public InquiryDto getInquiryByInquiryNoAndMemberId(int inquiryNo, Long memberId) {
		
		InquiryDto inquiry = null;
		
		String sql = "SELECT inquiry_no, member_id\r\n"
				+ "FROM inquiry\r\n"
				+ "WHERE inquiry_no = ?\r\n"
				+ "  AND member_id = ?";
		
		   try {

		        con = DBConnection.getConnection();
		        ps = con.prepareStatement(sql);

		        ps.setInt(1, inquiryNo);
		        ps.setLong(2, memberId);

		        rs = ps.executeQuery();

		        if (rs.next()) {

		            inquiry = new InquiryDto();

		            inquiry.setInquiry_no(rs.getInt("inquiry_no"));

		            inquiry.setMember_id(rs.getLong("member_id"));
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();

		    } finally {
		        DBConnection.closeDB(con, ps, rs);
		    }
		
		return inquiry;
	}

	
	// 문의글 삭제 — Service에서 권한 확인 후 호출
	public int deleteInquiry(int inquiryNo) {

	    String sql = "DELETE FROM inquiry WHERE inquiry_no = ?";

	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setInt(1, inquiryNo);

	        return ps.executeUpdate();

	    } catch (SQLException e) {
	        throw new RuntimeException(
	            "문의 삭제 DB 처리에 실패했습니다.", e
	        );
	    }
	}

	// 관리자 답변 등록
	public int saveInquiryAnswer(int inquiryNo, String answerContent) {
		int result = 0;
		
		String sql ="update inquiry\r\n"
				+ "set answer_content = ?,\r\n"
				+ "	answered_at = CURRENT_TIMESTAMP,\r\n"
				+ "    status= 'COMPLETED'\r\n"
				+ "where inquiry_no = ?;";
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
				ps.setString(1, answerContent);
				ps.setInt(2, inquiryNo);
				
			result = ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	// 관리자 문의 답변삭제
	public int deleteInquiryAnswer(int inquiryNo) {
		int result =0;
		
		String sql ="UPDATE atumari.inquiry\r\n"
				+ "SET answer_content = NULL,\r\n"
				+ "    answered_at = NULL,\r\n"
				+ "    status = 'WAITING'\r\n"
				+ "WHERE inquiry_no = ?";
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql);
				ps.setInt(1, inquiryNo);
				
			result = ps.executeUpdate();
			
		}catch (SQLException e) {
		    throw new RuntimeException("답변 삭제 DB 처리 실패", e);
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return result;
	}


}
