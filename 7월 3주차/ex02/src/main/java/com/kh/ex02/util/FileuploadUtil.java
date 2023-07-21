package com.kh.ex02.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class FileuploadUtil {
	// 스프링에서 관리하진 않고, 편의를 위해 만든 클래스
	
	// 사용자가 업로드한 파일을 지정경로에 저장
	public static String upload(byte[] bytes, // 데이터 자체
								String uploadPath, // 저장경로
								String originalFilename) {
		
		UUID uuid = UUID.randomUUID(); // 중복되지 않는 문자열 생성
		String dirPath = makeDir(uploadPath);
		String filename = uuid + "_" + originalFilename;
		String saveFilename = // 저장될 파일 경로+이름
				uploadPath + "/" + dirPath + "/" + filename; 
		File target = new File(saveFilename);
		try {
			FileCopyUtils.copy(bytes, target); // spring에서 제공하는 객체. 경로에 파일 복사
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		// 이미지 여부에 따라 썸네일 이미지 생성하기(ImageScalr 라이브러리 사용)
		String thumbnailPath =  makeThumbnail(uploadPath, dirPath, filename);
		System.out.println("thumbnailPath: " + thumbnailPath);
		
		String filePath = saveFilename.substring(uploadPath.length());
		return filePath;
	}
	
	// 이미지 여부에 따라 썸네일 이미지 생성하기(ImageScalr 라이브러리 사용)
	private static String makeThumbnail(
				String uploadPath, String dirPath, String filename) {
		
		String sourcePath =  
				uploadPath + "/" + dirPath + "/" + filename; 
		String thumbnailPath = 
				uploadPath + "/" + dirPath + "/s_" + filename; 
		try {
			// 원본 이미지를 메모리에 로딩
			BufferedImage sourceImage = ImageIO.read(new File(sourcePath));
			
			// 저장할 썸네일(높이 100px로 너비 자동 조절)
			BufferedImage destImage = 
					Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, 
									Scalr.Mode.FIT_TO_HEIGHT, 100);
			
			// 썸네일 이미지 저장
			File f = new File(thumbnailPath);
			int dotIndex = filename.lastIndexOf(".");
			String formatName = filename.substring(dotIndex + 1); // 확장자
			ImageIO.write(destImage, formatName.toUpperCase(), f);
			
			return thumbnailPath;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 날짜별 폴더 만들어서 업로드 파일 저장하기 -> 날짜 구하는 메서드
	private static String makeDir(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		String dirPath = year + "/" + month + "/" + date;
		
		// C:/zzz/upload/2023/7/21
		File f = new File(uploadPath + "/" + dirPath); 
		if(!f.exists()) {
			f.mkdirs(); // 년월일에 해당하는 여러개의 폴더를 만들어야 함 -> mkdirs()
		}
		
		return dirPath;
	}

}
