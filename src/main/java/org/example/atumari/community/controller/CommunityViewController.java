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
    	
    	CommunityDto cmtydto = communityService.getCommunityView(cmtyno);
    	
    	
    	request.setAttribute("cmtydto", cmtydto);
    	request.setAttribute("cmtyFiles", communityService.getCommunityFiles(cmtyno));
    	request.setAttribute("commentList", communityService.getCommunityCommentView(cmtyno));
    	
        request.getRequestDispatcher("/WEB-INF/views/community/view_test.jsp")
                .forward(request, response);
        
        
    }
}
