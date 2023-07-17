package com.jh.ex02.vo;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardVo {

	private int bno;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	
	public BoardVo(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
}
