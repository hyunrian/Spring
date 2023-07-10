package com.kh.ex01.dao;

import com.kh.ex01.vo.MemberVo;

public interface MemberDao {

	public String readTime() throws Exception;
	
	public void createMember(MemberVo vo) throws Exception;
	
	public MemberVo readMember(String userid) throws Exception;
	
	public MemberVo readWithPw(String userid, String userpw) throws Exception;
	
}
