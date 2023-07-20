package com.kh.ex02.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class FileuploadUtil {
	// 스프링에서 관리하진 않고, 편의를 위해 만든 클래스
	
	// 사용자가 업로드한 파일을 지정경로에 저장
	public static String upload(
			byte[] bytes, // 데이터 자체
			String uploadPath, // 저장경로
			String originalFilename) {
		
		UUID uuid = UUID.randomUUID(); // 중복되지 않는 문자열 생성
		String saveFilename = // 저장될 파일 이름
				uploadPath + "/" + uuid + "_" + originalFilename; 
		File target = new File(saveFilename);
		try {
			// spring에서 제공하는 객체. 경로에 파일 복사
			FileCopyUtils.copy(bytes, target);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return saveFilename;
	}

}
