package com.yse.dev.book.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.yse.dev.book.dto.BookCreateDTO;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class BookServiceTest {

	@Autowired
	private BookService bookService;
	
	@Test
	@DisplayName("데이터 10개 추가")
	public void testInsert() { // 서버가 실행되어 있으면 중단시키고 실행
		for (int i = 1; i <= 10; i++) {
			BookCreateDTO dto = new BookCreateDTO();
			dto.setTitle("제목-" + i);
			dto.setPrice(10000 + i);
			bookService.insert(dto);
		}
		System.out.println("insert completed");
	}
	
}
