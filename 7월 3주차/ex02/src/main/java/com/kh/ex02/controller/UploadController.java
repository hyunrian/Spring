package com.kh.ex02.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	// 비동기방식으로 파일 업로드 처리하기
	
	@Resource(name = "uploadPath") // bean에 등록한 id
	private String uploadPath; // bean으로 등록한 경로 가져오기
	
	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8") // 웹(콘솔)에서 한글 인식하도록 mime-type 설정
	public String uploadFile(MultipartFile file) {
		System.out.println("original filename : " + file.getOriginalFilename());
		return file.getOriginalFilename();
	} 
	

}
