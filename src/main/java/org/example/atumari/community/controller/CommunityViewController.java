package org.example.atumari.community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.example.atumari.community.dto.CommunityCommentDto;
import org.example.atumari.community.dto.CommunityDto;
import org.example.atumari.community.service.CommunityService;

@WebServlet("/community/view")
public class CommunityViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	CommunityService communityService = new CommunityService();
    	
    	long cmtyno = Integer.parseInt(request.getParameter("cmtyNo"));
    	
    	int hitCount = communityService.setHitCount(cmtyno);
		if(hitCount != 1) System.out.println("조회수 갱신 실패!!");
    	
    	CommunityDto cmtydto = communityService.getCommunityView(cmtyno);
    	
    	request.setAttribute("cmtydto", cmtydto);
    	request.setAttribute("cmtyFiles", communityService.getCommunityFiles(cmtyno));
    	request.setAttribute("commentList", communityService.getCommunityCommentView(cmtyno));
    	request.setAttribute("sessionEmail", (String) request.getSession().getAttribute("sessionEmail"));
    	
        request.getRequestDispatcher("/WEB-INF/views/community/view_test.jsp")
                .forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	CommunityService communityService = new CommunityService();
        // 게시물 번호
    	long cmtyNo = Integer.parseInt(request.getParameter("cmty_no"));
        // 부모 댓글 번호
        String parentNoParam = request.getParameter("parent_no");
        // 로그인 사용자 (작성자)
        String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
        // 댓글 내용
        String content = request.getParameter("content");
        	content = getSingleQuot(content);

        Long parentNo = null;

        // 답글인 경우
        if (parentNoParam != null && !parentNoParam.isBlank()) {
            parentNo = Long.parseLong(parentNoParam);
        }

        System.out.println("cmtyNo :"+cmtyNo);
        System.out.println("parentNo :"+parentNo);
        System.out.println("sessionEmail :"+sessionEmail);
        System.out.println("content :"+content);
        // DTO 생성
        CommunityCommentDto comment = new CommunityCommentDto(cmtyNo,parentNo,sessionEmail,content);

        // 댓글 저장
        int result = communityService.writeComment(comment);

        // 다시 게시물 페이지로 이동
        response.sendRedirect(
            request.getContextPath() + "/community/view?cmtyNo=" + cmtyNo
        );
    }
  //작은따옴표 변환
    private String getSingleQuot(String str) {
		str = str.replaceAll("'", "&#39;");
		return str;
	}
}
