package com.kh.ex02.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ex02.util.FileuploadUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	// 비동기방식으로 파일 업로드 처리하기
	// <form> 태그 내 다른 <input>은 넘어오지 않고, 파일만 넘어옴
	
	@Resource(name = "uploadPath") // bean에 등록한 id
	private String uploadPath; // bean으로 등록한 경로 가져오기
	
	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8") // 웹(콘솔)에서 한글 인식하도록 mime-type 설정
	public String uploadFile(MultipartFile file) {
		System.out.println("original filename : " + file.getOriginalFilename());
		try {
			byte[] bytes = file.getBytes(); // binary data
			String saveFilename = FileuploadUtil.upload(
					bytes, uploadPath, file.getOriginalFilename());
			return saveFilename;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	// 썸네일 이미지 register.jsp로 전달하기
	@ResponseBody
	@RequestMapping(value = "/displayImage", method = RequestMethod.GET)
	public byte[] displayImage(String thumbnail) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(uploadPath + thumbnail);
			byte[] data = IOUtils.toByteArray(fis); // commons-io 라이브러리 사용
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 첨부파일 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
	public String deleteFile(@RequestBody String filename) {
//		System.out.println("filename:" + filename);
		boolean isImage = FileuploadUtil.isImage(filename);
		
		// 이미지 파일이라면 썸네일 이미지 삭제
		if (isImage) {
			int slashIndex = filename.lastIndexOf("/");
			String front = filename.substring(0, slashIndex + 1);
			String back = filename.substring(slashIndex + 1);
			String thumbnail = front + "s_" + back;
			File f = new File(uploadPath + thumbnail);
			if (f.exists()) f.delete();
		}
		// 원본파일 삭제
		File f = new File(uploadPath + filename);
		if (f.exists()) f.delete();
		
		return "SUCCESS";
	}

}
