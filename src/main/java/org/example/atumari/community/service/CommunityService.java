package org.example.atumari.community.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.example.atumari.common.fileupload.FileService;
import org.example.atumari.common.fileupload.StoredFile;
import org.example.atumari.common.util.Pagination;
import org.example.atumari.community.dao.CommunityCommentDao;
import org.example.atumari.community.dao.CommunityDao;
import org.example.atumari.community.dao.CommunityFileDao;
import org.example.atumari.community.dto.CommunityFileDto;
import org.example.atumari.community.dto.CommunityListPageDto;
import org.example.atumari.community.dto.CommunityCommentDto;
import org.example.atumari.community.dto.CommunityDto;
import org.example.atumari.config.FileConfig;

import jakarta.servlet.http.Part;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class CommunityService {
	
	private static int PAGE_SIZE = 10;
    private static final int PAGE_GROUP_SIZE = 5;
	private final CommunityDao cmtydao = new CommunityDao();
	private final CommunityFileDao cmtyFileDao = new CommunityFileDao();
	private final CommunityCommentDao cmtyCommentDao = new CommunityCommentDao();
    private final FileService fileService = new FileService();

    //게시물 리스트
    public CommunityListPageDto getCommunityList(int currentPage, String searchType, String search, int postCount) {
		//검색 조건 정규화
    	String normalizedSearchType = normalizeSearchType(searchType);
        String normalizedKeyword = search == null ? "" : search.trim();
        int totalCount = cmtydao.countCommunity(normalizedSearchType, normalizedKeyword);
        PAGE_SIZE = postCount == 1 ? 10 : postCount;
        Pagination pagination = Pagination.of(currentPage, PAGE_SIZE, PAGE_GROUP_SIZE, totalCount);

        List<CommunityDto> cmtyList = cmtydao.getCommunityList(pagination.getPageSize(), pagination.getOffset(), normalizedSearchType, normalizedKeyword);

        return new CommunityListPageDto(cmtyList, pagination.getCurrentPage(), pagination.getPageSize(), pagination.getTotalCount(), pagination.getTotalPage(), pagination.getStartPage(), pagination.getEndPage(), normalizedSearchType, normalizedKeyword);
    
	}
    
	// 새로운 게시물 저장
	public int write(CommunityDto cmtydto, Part imagePart) {
	    int result = 0;
	    try {
	        // 1. 게시물 저장
	        Long cmtyNo = cmtydao.communitySave(cmtydto);
	        // 게시물 저장 실패
	        if (cmtyNo == null) {
	            result = 0;
	        }
	        if(cmtyNo != 0) {
	        	// 2. 사진이 있을 때 첨부파일 저장
		        if (imagePart != null && imagePart.getSize() > 0) {
		            // 실제 파일 저장
		            try {
		                result = saveFile(imagePart, cmtyNo);
		            } catch (RuntimeException e) {
		                CommunityDao.deleteCommunity(cmtyNo);
		                throw e;
		            }
		        } else {
		            // 사진이 없어도 게시물 등록 성공
		            result = 1;
		        }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 0;
	    }
	    return result;
	}
	
	//게시물 상세조회
	public CommunityDto getCommunityView(long cmtyno) {
		CommunityDao cmtydao = new CommunityDao();
		CommunityFileDao cmtyfiledao = new CommunityFileDao();
		
		CommunityDto cmtydto = cmtydao.getCommunityView(cmtyno);
		CommunityFileDto cmtyfile = null;
		
		
		return cmtydto;
	}
	
	//파일 저장
	private int saveFile(Part file , Long cmtyNo) {
		//리턴 값
		int result = 0;
		//오류시 삭제용
		String uploadedKey = null;
	    	try {
	        		//저장 경로
	                StoredFile storedFile = fileService.saveFile(file, "community");
	                //원본 파일명
	                String originalFileName = storedFile.getOriginalFileName();
	                //실제 저장된 파일명
	                String objectKey = storedFile.getStoredFileName();
	                uploadedKey = objectKey;
	                
	                //파일 DTO
		            CommunityFileDto filedto =
		                    new CommunityFileDto(cmtyNo,originalFileName,objectKey);
	               
	                if (cmtyFileDao.fileSave(filedto) != 1) {
	                    throw new RuntimeException("커뮤니티 첨부파일 정보 저장에 실패했습니다.");
	                }
	                //저장 성공시 리턴값 수정
	                result = 1;
	        } catch (RuntimeException e) {
	                try {
	                    fileService.deleteFile(uploadedKey);
	                } catch (RuntimeException ignored) {
	                    // 원래 업로드 실패 원인을 유지합니다.
	                }
	            throw new RuntimeException("커뮤니티 첨부파일 저장에 실패했습니다.", e);
	        }
	        return result;
	    }
	// 첨부파일 목록 조회
	public List<CommunityFileDto> getCommunityFiles(long cmtyno) {
	    return cmtyFileDao.getCmtyFiles(cmtyno);
	}
	public CommunityFileDto getCommunityFile(long fileNo) {
	    return cmtyFileDao.getCmtyFileByNo(fileNo);
	}
	// 댓글 조회
	public List<CommunityCommentDto> getCommunityCommentView(long cmtyno){
		return cmtyCommentDao.getCommunityCommentView(cmtyno);
	}

	// S3에서 실제 파일 데이터 조회
	public ResponseBytes<GetObjectResponse> downloadCommunityFile(
	        CommunityFileDto file) {

	    if (file == null) {
	        throw new IllegalArgumentException("첨부파일을 찾을 수 없습니다.");
	    }

	    return fileService.downloadFile(file.getSave_file_name());
	}
	
	
	
	//검색 조건 정규화
	private String normalizeSearchType(String searchType) {
		if(searchType == null) searchType = "content";
		return searchType;
    }
}

