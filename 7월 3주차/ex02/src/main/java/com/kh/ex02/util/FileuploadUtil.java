package com.kh.ex02.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class FileuploadUtil {
	// 스프링에서 관리하진 않고, 편의를 위해 만든 클래스
	
	// 사용자가 업로드한 파일을 지정경로에 저장
	public static String upload(byte[] bytes, // 데이터 자체
								String uploadPath, // 저장경로
								String originalFilename) {
		
		UUID uuid = UUID.randomUUID(); // 중복되지 않는 문자열 생성
		String dir = makeDir(uploadPath);
		String saveFilename = // 저장될 파일 이름
				dir + "/" + uuid + "_" + originalFilename; 
		File target = new File(saveFilename);
		try {
			FileCopyUtils.copy(bytes, target); // spring에서 제공하는 객체. 경로에 파일 복사
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return saveFilename;
	}
	
	// 날짜별 폴더 만들어서 업로드 파일 저장하기 -> 날짜 구하는 메서드
	private static String makeDir(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		String dirString = year + "/" + month + "/" + date;
		
		// C:/zzz/upload/2023/7/21
		File f = new File(uploadPath + "/" + dirString); 
		if(!f.exists()) {
			f.mkdirs(); // 년월일에 해당하는 여러개의 폴더를 만들어야 함 -> mkdirs()
		}
		
		return uploadPath + "/" + dirString;
	}

}
