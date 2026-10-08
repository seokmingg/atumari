package org.example.atumari.community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.atumari.community.dto.CommunityDto;
import org.example.atumari.community.dto.CommunityListPageDto;
import org.example.atumari.community.service.CommunityService;


@WebServlet("/community")
public class CommunityListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	CommunityService communityService = new CommunityService();
    	
    	//리스트 불러오기 위한 정보들 
    	int currentPage = parsePage(request.getParameter("page"));
    	int postCount = 0;
		if(request.getParameter("postCount") == null) {
			postCount = 10;
		} else {
			postCount = Integer.parseInt(request.getParameter("postCount"));
		}
        String searchType = request.getParameter("searchType");
        String search = request.getParameter("search");
        
        //리스트 불러오기
        CommunityListPageDto cmtyPage = communityService.getCommunityList(
                currentPage, searchType, search, postCount);
        //인기글 리스트 불러오기
        List<CommunityDto> cmtyHits = communityService.getCommunityHitList();
       
        request.setAttribute("cmtyPage", cmtyPage);
        request.setAttribute("cmtyHits", cmtyHits);
        
        request.getRequestDispatcher("/WEB-INF/views/community/list.jsp")
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	CommunityService communityService = new CommunityService();
    	
    	//리스트 불러오기 위한 정보들 
    	int currentPage = parsePage(request.getParameter("page"));
    	int postCount = 0;
		if(request.getParameter("postCount") == null) {
			postCount = 10;
		} else {
			postCount = Integer.parseInt(request.getParameter("postCount"));
		}
        String searchType = request.getParameter("searchType");
        String search = request.getParameter("search");
        
        //리스트 불러오기
        CommunityListPageDto cmtyPage = communityService.getCommunityList(
                currentPage, searchType, search, postCount);
        //인기글 리스트 불러오기
        List<CommunityDto> cmtyHits = communityService.getCommunityHitList();
        
        request.setAttribute("cmtyPage", cmtyPage);
        request.setAttribute("cmtyHits", cmtyHits);
        
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
