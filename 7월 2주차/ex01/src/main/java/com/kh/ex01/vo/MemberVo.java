package com.kh.ex01.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberVo {
	String userid;
	String userpw;
	String username;
	String email;
	Timestamp regdate;
	Timestamp updatedate;
}
