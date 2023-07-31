package com.kh.ex02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {

	private String u_id;
	private String u_pw;
	private boolean useCookie; // 아이디 저장 여부

}
