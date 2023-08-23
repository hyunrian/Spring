package com.yse.dev.book.dto;

import java.time.LocalDateTime;

import com.yse.dev.book.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter // factory 패턴을 이용할 거라 setter 생성을 하지 않음 
public class BookEditResponseDTO {

	private Integer bookId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	public BookEditResponseDTO fromBook(Book book) {
		this.bookId = book.getBookId();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.insertDateTime = book.getInsertDateTime();
		return this;
	}
	
	// factory 패턴으로 필드값 정의(set)
	public static BookEditResponseDTO BookFactory(Book book) {
		
		BookEditResponseDTO bookEditResponseDTO = 
				new BookEditResponseDTO();
		bookEditResponseDTO.fromBook(book);
		
		return bookEditResponseDTO;
	}
	
}
