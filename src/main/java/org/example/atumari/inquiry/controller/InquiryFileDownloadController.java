package org.example.atumari.inquiry.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.example.atumari.inquiry.dto.InquiryDto;
import org.example.atumari.inquiry.dto.InquiryFileDto;
import org.example.atumari.inquiry.service.InquiryService;
import org.example.atumari.inquiry.service.InquiryViewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

/**
 * Servlet implementation class InquiryFileDownloadController
 */
@WebServlet("/inquiry/file/download")
public class InquiryFileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final InquiryService inquiryService = new InquiryService();
	private final InquiryViewService inquiryViewService = new InquiryViewService();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		try {
			// 1. 요청받은 파일 번호
			int fileNo = Integer.parseInt(request.getParameter("fileNo"));
			
			 // 2. fileNo를 이용해 DB에서 첨부파일 정보를 조회
			InquiryFileDto fileDto = inquiryService.getInquiryFile(fileNo);
			
			// 3. 등록된 파일 정보가 없으면 404를 반환한다.
			   if (fileDto == null) {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND);
	                return;
	            }
			// 4. 해당 파일이 실제로 속한 문의 조회
			   InquiryDto inquiryDto = inquiryViewService.getInquiryView(fileDto.getInquiry_no());
			   
			   if(inquiryDto == null) {
				   response.sendError(HttpServletResponse.SC_NOT_FOUND);
				   return;
			   }
			   
			// 5. 비공개 문의: 본인 또는 관리자만 다운로드
			   if(!inquiryDto.isPublic()) {
				   HttpSession session = request.getSession(false);
				   
		            Long memberId = session == null ? null
		                : (Long) session.getAttribute("sessionId");

		            boolean isOwner = memberId != null &&
		                memberId.equals(inquiryDto.getMember_id());

		            boolean isAdmin = session != null &&
		            		"admin".equals(session.getAttribute("sessionLevel"));

		            if (!isOwner && !isAdmin) {
		                response.sendError( HttpServletResponse.SC_FORBIDDEN);
		                return;
		            }
			   }
			// 6. S3에서 실제 파일 다운로드	   
	       ResponseBytes<GetObjectResponse> object = inquiryService.downloadInquiryFile(fileDto);
	       // 7. Content-type
            String contentType = object.response().contentType();
            // 콘텐츠 타입이 없다면 기본 바이너리 타입을 사용한다.
            response.setContentType(contentType == null ? "application/octet-stream" : contentType);
           
            // 8. 파일크기
            response.setContentLength(object.asByteArray().length);
            // 9. 원본 파일명 인코딩
            String encodedName = URLEncoder.encode(
            		fileDto.getOriginal_file_name(), StandardCharsets.UTF_8).replace("+", "%20");
            // 10. 다운로드 파일명 지정
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename*=UTF-8''" + encodedName
            );   
			
            // 11. 실제 파일 데이터를 브라우저로 전송
            response.getOutputStream().write(object.asByteArray());

            response.getOutputStream().flush();
			   
		}catch(NumberFormatException e) {
			 response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}

}
