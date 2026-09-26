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
import org.example.atumari.community.dao.CommunityDao;
import org.example.atumari.community.dao.CommunityFileDao;
import org.example.atumari.community.dto.CommunityFileDto;
import org.example.atumari.community.dto.CommunityPostDto;
import org.example.atumari.config.FileConfig;

import jakarta.servlet.http.Part;

public class CommunityService {
	
	private static final int PAGE_SIZE = 10;
    private static final int PAGE_GROUP_SIZE = 5;
	private final CommunityDao cmtydao = new CommunityDao();
	private final CommunityFileDao cmtyFileDao = new CommunityFileDao();
    private final FileService fileService = new FileService();


	// 새로운 게시물 저장
	public int write(CommunityPostDto cmtydto, Part imagePart) {
	    int result = 0;
	    try {
	        // 1. 게시물 저장
	        Long cmtyNo = cmtydao.communitySave(cmtydto);
	        // 게시물 저장 실패
	        if (cmtyNo == null) {
	            result = 0;
	        }
	        if(cmtyNo != 0) {
	        	// 2. 사진이 있을 때만 파일 저장
		        if (imagePart != null && imagePart.getSize() > 0) {

//		            // 원본 파일명
//		            String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
//		            // 저장 파일명
//		            String randomName = UUID.randomUUID().toString();
//		            String saveFileName = randomName + "-" + originalFileName;
//		            // 업로드 폴더
//		            String uploadPath = FileConfig.getUploadPath();
//		            Path uploadDir = Paths.get(uploadPath, "community");
//		            Files.createDirectories(uploadDir);

		            // 실제 파일 저장
		            try {
		                saveFile(imagePart, cmtyNo);
		            } catch (RuntimeException e) {
		                CommunityDao.deleteCommunity(cmtyNo);
		                throw e;
		            }

//		            // 파일 DTO
//		            CommunityFileDto filedto =
//		                    new CommunityFileDto(cmtyNo,originalFileName,saveFileName);
//		            // 파일 DB 저장
//		            result = cmtydao.fileSave(filedto);
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
	
	//파일 저장
	 private void saveFile(Part file , Long cmtyNo) {
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
	        } catch (RuntimeException e) {
	                try {
	                    fileService.deleteFile(uploadedKey);
	                } catch (RuntimeException ignored) {
	                    // 원래 업로드 실패 원인을 유지합니다.
	                }
	            throw new RuntimeException("커뮤니티 첨부파일 저장에 실패했습니다.", e);
	        }
	    }
}

