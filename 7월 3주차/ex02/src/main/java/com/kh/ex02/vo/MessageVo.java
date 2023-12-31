package com.kh.ex02.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageVo {
	
	private int m_id;
	private String targetid;
	private String sender;
	private String message;
	private Timestamp opendate;
	private Timestamp senddate;

}
