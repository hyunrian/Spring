package com.yse.dev.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookCreateDTO {

	@NonNull
	private String title;
	
	@NonNull
	private Integer price;
}
