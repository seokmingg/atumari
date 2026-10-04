package org.example.atumari.inquiry.controller;

import java.io.IOException;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.service.AdminService;
import org.example.atumari.inquiry.service.InquiryViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/inquiry/admin/answer/delete")
public class InquiryAdminAnswerDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminService adminService = new AdminService();
	private final InquiryViewService inquiryViewService = new InquiryViewService();
       
 
	protected void doGet(HttpServletRequest request,
						HttpServletResponse response)
            		throws ServletException, IOException {

		 String view = "/WEB-INF/views/inquiry/admin_inquiry_view.jsp";
	     request.getRequestDispatcher(view).forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 여부 + 관리자 확인
	    HttpSession session = request.getSession(false);
	    
	    if(session == null || 
	    		!"admin".equals(session.getAttribute("sessionLevel")) ) {
	    	response.sendRedirect(request.getContextPath() + "/member/login");
	    	return;
	    }
	    
	    // 문의 글 번호 , 사용자가 값을 문자열이나 확인불가한 문자로 값을 변경할 경우를 대비
	    int inquiryNo;
	    try {
	        inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
	        //양수가 맞는 지 확인
	        if (inquiryNo <= 0) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	            return;
	        }
	    } catch (NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        return;
	    }
	    
	    // 삭제 실행
	    try {
	        adminService.deleteAnswer(inquiryNo);

	    } catch (RuntimeException e) {
	        getServletContext().log("관리자 답변 삭제 실패", e);

	        request.setAttribute(
	            "errorMessage",
	            "回答の削除中にエラーが発生しました。"
	        );

	        // 오류 메시지와 함께 표시할 상세 데이터 조회
	        InquiryDto inquiryDto =
	            inquiryViewService.getInquiryView(inquiryNo);

	        if (inquiryDto == null) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	            return;
	        }

	        request.setAttribute("inquiryDto", inquiryDto);
	        request.setAttribute(
	            "fileDtos",
	            inquiryViewService.getInquiryFiles(inquiryNo)
	        );

	        request.getRequestDispatcher(
	            "/WEB-INF/views/inquiry/admin_inquiry_view.jsp"
	        ).forward(request, response);

	        return;
	    }

	    // 삭제 성공: 조회 컨트롤러에서 상세 데이터를 다시 조회
	    response.sendRedirect(
	        request.getContextPath()
	        + "/inquiry/admin/view?inquiryNo="
	        + inquiryNo
	    );
		}

}
