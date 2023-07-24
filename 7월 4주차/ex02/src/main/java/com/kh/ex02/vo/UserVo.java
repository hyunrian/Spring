package com.kh.ex02.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVo {
	
	private String u_id;
	private String u_pw;
	private String u_name;
	private int u_point;

}
