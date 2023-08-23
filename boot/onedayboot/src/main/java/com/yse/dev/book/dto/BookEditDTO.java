package com.yse.dev.book.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.yse.dev.book.entity.Book;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookEditDTO {

	@NonNull
	@Positive // 양수값만 허용
	private Integer bookId;
	
	@NonNull
	@NotBlank
	private String title;
	
	@NonNull
	@Min(1000) // 최소값 설정
	private Integer price;
	
	public Book fill(Book book) { // entity 객체에 저장해야 save했을 때 테이블로 값이 들어감
		book.setTitle(title);
		book.setPrice(price);
		return book;
	}
}
