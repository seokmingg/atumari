package org.example.atumari.community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.atumari.community.dto.CommunityDto;
import org.example.atumari.community.dto.CommunityListPageDto;
import org.example.atumari.community.service.CommunityService;


@WebServlet("/community")
public class CommunityListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	CommunityService communityService = new CommunityService();
    	
    	int currentPage = parsePage(request.getParameter("page"));
        String searchType = request.getParameter("searchType");
        String search = request.getParameter("search");
        
        CommunityListPageDto cmtyPage = communityService.getCommunityList(
                currentPage, searchType, search);
        
        request.setAttribute("cmtyPage", cmtyPage);
        
    	
        request.getRequestDispatcher("/WEB-INF/views/community/list.jsp")
                .forward(request, response);
    }
    private int parsePage(String pageValue) {
        try {
            return Math.max(1, Integer.parseInt(pageValue));
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}
