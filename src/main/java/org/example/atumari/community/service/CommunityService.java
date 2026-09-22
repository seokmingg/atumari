package org.example.atumari.community.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.example.atumari.common.fileupload.FileService;
import org.example.atumari.community.dao.CommunityDao;
import org.example.atumari.community.dto.CommunityFileDto;
import org.example.atumari.community.dto.CommunityPostDto;
import org.example.atumari.config.FileConfig;

import jakarta.servlet.http.Part;

public class CommunityService {

	private final CommunityDao cmtydao = new CommunityDao();
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

		            // 원본 파일명
		            String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
		            // 저장 파일명
		            String randomName = UUID.randomUUID().toString();
		            String saveFileName = randomName + "-" + originalFileName;
		            // 업로드 폴더
		            String uploadPath = FileConfig.getUploadPath();

		            Path uploadDir = Paths.get(uploadPath, "community");
		            Files.createDirectories(uploadDir);


		            // 실제 파일 저장
		            Path savePath = uploadDir.resolve(saveFileName);
		            imagePart.write(savePath.toString());


		            // 파일 DTO
		            CommunityFileDto filedto =
		                    new CommunityFileDto(cmtyNo,originalFileName,saveFileName);
		            // 파일 DB 저장
		            result = cmtydao.fileSave(filedto);
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
}

