package com.yse.dev.book.dto;

import java.time.LocalDateTime;

import com.yse.dev.book.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter // factory 패턴을 이용할 거라 setter 생성을 하지 않음 
public class BookReadResponseDTO {

	private Integer bookId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	public BookReadResponseDTO fromBook(Book book) {
		this.bookId = book.getBookId();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.insertDateTime = book.getInsertDateTime();
		return this;
	}
	
	// factory 패턴으로 필드값 정의(set)
	public static BookReadResponseDTO BookFactory(Book book) {
		
		BookReadResponseDTO bookReadResponseDTO = 
				new BookReadResponseDTO();
		bookReadResponseDTO.fromBook(book);
		
		return bookReadResponseDTO;
	}
	
}
