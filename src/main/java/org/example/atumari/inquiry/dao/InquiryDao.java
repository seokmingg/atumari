package org.example.atumari.inquiry.dao;

import org.example.atumari.inquiry.dto.InquiryDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.common.database.DBConnection;

public class InquiryDao {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 문의 글 등록
	public int insertInquiry(InquiryDto inquiry) {
		
		int inquiry_no=0;
		
		String sql ="INSERT INTO inquiry " +
			    "(member_id, title, writer, is_public, content, email) " +
			    "VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//두번째 인수 의미:INSERT에서 DB가 자동 생성한 키도 나한테 돌려줘라는 의미
				ps.setLong(1, inquiry.getMember_id());
				ps.setString(2, inquiry.getTitle());
				ps.setString(3, inquiry.getWriter());
				ps.setBoolean(4, inquiry.isPublic());
				ps.setString(5, inquiry.getContent());
				ps.setString(6, inquiry.getEmail());
				
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
	public List<InquiryDto> findInquiryList(String searchType, String keyword, int pageSize,int offset){
		List<InquiryDto> inquiryList = new ArrayList<>();
		
		// StringBuilder로 생성 -> 이유: sql.append 기능(sql문 덧붙이기)을 쓰기위해
		StringBuilder sql = new StringBuilder("""
				 SELECT i.inquiry_no,
			           i.title,
			           i.writer,
			           i.status,
			           i.created_at,
		           EXISTS (
		               SELECT 1
		               FROM inquiry_file f
		               WHERE f.inquiry_no = i.inquiry_no
		           ) AS file_is
				  FROM inquiry i
				""");
		
		// 검색어와 올바른 검색 조건이 있는지 확인
		boolean hasSearch = keyword != null && 
								!keyword.isBlank() &&
									("title".equals(searchType) || "writer".equals(searchType));
		
		// 검색 조건만 동적으로 추가
		if(hasSearch) {
			if("title".equals(searchType)) {
				sql.append(" where i.title like ? ");//주의: 앞뒤 공백 주기, 앞뒷문장과 붙으면 안됨
			}else if("writer".equals(searchType)) {
				sql.append(" where i.writer like ? ");
			}
		}
		
		//공통적으로 필요한 부분(페이지네이션)
		sql.append("ORDER BY i.created_at DESC ");
		sql.append("LIMIT ? OFFSET ?");//LIMIT 몇개의 행을 가져올 것인가, OFFSET 앞에서 몇개의 행을 건너뛸것 인가
				
		
		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			
			int parameterIndex =1;// 만든이유는 검색어가 있을 때 없을 때 바인딩할 인덱스가 달라지기에 
			
			// 검색어 있을 때만 바인딩에 추가
				if(hasSearch) {
					ps.setString(parameterIndex++, "%"+keyword+"%");
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
	public int getTotalInquiryCount(String searchType, String keyword) {
		
		int totalCount =0;
		
		StringBuilder sql = new StringBuilder("""
				select count(*)
				from inquiry
				""");
		
		// 검색어와 올바른 검색 조건이 있는지 확인
		boolean hasSearch = keyword != null && 
								!keyword.isBlank() &&
									("title".equals(searchType) || "writer".equals(searchType));
		
		// 검색 조건만 동적으로 추가
		if(hasSearch) {
			if("title".equals(searchType)) {
				sql.append(" where i.title like ? ");//주의: 앞뒤 공백 주기, 앞뒷문장과 붙으면 안됨
			}else if("writer".equals(searchType)) {
				sql.append(" where i.writer like ? ");
			}
		}
		
		try{
			con = DBConnection.getConnection();
			 ps = con.prepareStatement(sql.toString());

		        if (hasSearch) {
		            ps.setString(1, "%" + keyword + "%");
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

}
