package org.example.atumari.inquiry.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;
import org.example.atumari.inquiry.service.InquiryService;
import org.example.atumari.inquiry.service.InquiryViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class InquiryUpdateController
 * Controller에서 파일을 받으려면 하단의 MultipartConfig가 필요
 */
@WebServlet("/inquiry/update")
@MultipartConfig
public class InquiryUpdateController extends HttpServlet {
	
	  private final InquiryViewService inquiryViewService = new InquiryViewService();
	  private final InquiryService inquiryService = new InquiryService();
	  
	  @Override
	    protected void doGet(HttpServletRequest request,
	                         HttpServletResponse response)
	            throws ServletException, IOException {
		  
		  // doGet: ViewService를 통해 저장된 상세 문의글 가져오는 역할 
		  
		  // 로그인 여부 확인
		    HttpSession session = request.getSession(false);

		    if (session == null ||
		        session.getAttribute("sessionId") == null) {

		        response.sendRedirect(
		            request.getContextPath() + "/member/login"
		        );
		        return;
		    }
		    
		    Long memberId =
		            (Long) session.getAttribute("sessionId");
		    
		    // 수정할 문의 번호
		  	String inquiryNoParam = request.getParameter("inquiryNo");
	        int inquiryNo = Integer.parseInt(inquiryNoParam);
	        
	        // 문의글 상세조회
		  	InquiryDto inquiryDto = inquiryViewService.getInquiryView(inquiryNo);
		  	
		  	// 본인이 작성한 문의인지 확인
		  	if(inquiryDto == null || !memberId.equals(inquiryDto.getMember_id())) {
		  		
		  		response.sendRedirect(request.getContextPath() + "/inquiry/list");
		  	        return;
		  	}
		  	
		    // 첨부파일 상세조회
		  	List<InquiryFileDto> fileDtos = inquiryViewService.getInquiryFiles(inquiryNo);
		 
		    request.setAttribute("inquiryDto", inquiryDto);
	        request.setAttribute("fileDtos", fileDtos);

	        String view = "/WEB-INF/views/inquiry/inquiry_update.jsp";

	        request.getRequestDispatcher(view)
	               .forward(request, response);
	   }
	  
	   @Override
	    protected void doPost(HttpServletRequest request,
	                          HttpServletResponse response)
	                          throws ServletException, IOException {
		   //doPost: 사용자가 수정한 정보를 서버에 보내 저장하는 역할 수행
		   
		   // 로그인 여부 확인
		   HttpSession session = request.getSession(false);
		   if (session == null ||
				    session.getAttribute("sessionId") == null) {

				    response.sendRedirect(
				        request.getContextPath() + "/member/login"
				    );
				    return;
				}
		   
		   		int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));

			    String title = request.getParameter("title");
			  
			    String writer = (String) session.getAttribute("sessionName");
			    Long member_id = (Long)session.getAttribute("sessionId");
			    
			    boolean isPublic = "1".equals(request.getParameter("isPublic"));
			    boolean emailNotify = "1".equals(request.getParameter("emailNotify"));

			    String email = request.getParameter("email");
			    String content = request.getParameter("content");

			    InquiryDto inquiry = new InquiryDto();

			    inquiry.setInquiry_no(inquiryNo);
			    inquiry.setMember_id(member_id);
			    inquiry.setTitle(title);
			    inquiry.setWriter(writer);
			    inquiry.setPublic(isPublic);
			    inquiry.setEmail(email);
			    inquiry.setContent(content);
			    
			    // 삭제할 기존 첨부파일
			    String[] deleteFileNoParams = request.getParameterValues("deleteFileNo"); // 한번에 최대 3개의 파일 삭제가능하므로 parameterValues로 받기
			    List<Integer> deleteFileNos = new ArrayList<>();
			    
			    if(deleteFileNoParams != null) {
			    	
			    	for(String fileNo : deleteFileNoParams) {
			    		
			    		deleteFileNos.add(Integer.parseInt(fileNo));
			    	}
			    }
			    
			   // 새로 추가할 첨부파일
			   List<Part> newFiles = new ArrayList<>();
			   
			   for(Part part : request.getParts()) {
				   
				   if("inquiryFile".equals(part.getName()) && part.getSize()>0) {
					   newFiles.add(part);
				   }
			   }
			   
			   try {

				    // 문의 글 및 첨부파일 수정
				    inquiryService.updateInquiry(inquiry,deleteFileNos,newFiles,emailNotify);

				    // 수정 성공 → 상세 페이지로 이동
				    response.sendRedirect(
				        request.getContextPath()
				        + "/inquiry/view?inquiryNo="
				        + inquiryNo
				    );

				} catch (IllegalArgumentException e) { // RuntimeException의 자식 클래스로 구체적인 예외 먼저 작성

				    // 사용자 입력값 등의 검증 실패
				    request.setAttribute(
				        "errorMessage",
				        e.getMessage()
				    );

				    // 수정 화면에서 기존 첨부파일을 다시 보여주기 위해 조회
				    List<InquiryFileDto> fileDtos =
				        inquiryViewService.getInquiryFiles(inquiryNo);

				    request.setAttribute("inquiryDto", inquiry);
				    request.setAttribute("fileDtos", fileDtos);

				    request.getRequestDispatcher(
				        "/WEB-INF/views/inquiry/inquiry_update.jsp"
				    ).forward(request, response);

				} catch (RuntimeException e) {

				    // DB, 파일 저장 등 처리 중 오류
				    e.printStackTrace();

				    request.setAttribute(
				        "errorMessage",
				        "お問い合わせの修正中にエラーが発生しました。"
				    );

				    List<InquiryFileDto> fileDtos =
				        inquiryViewService.getInquiryFiles(inquiryNo);

				    request.setAttribute("inquiryDto", inquiry);
				    request.setAttribute("fileDtos", fileDtos);

				    request.getRequestDispatcher(
				        "/WEB-INF/views/inquiry/inquiry_update.jsp"
				    ).forward(request, response);
				}
			    
	   }
}
