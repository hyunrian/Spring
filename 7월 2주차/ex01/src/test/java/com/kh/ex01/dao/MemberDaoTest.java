package com.kh.ex01.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) // 읽을 설정파일 설정
public class MemberDaoTest {

	@Autowired
	// MemberDaoImpl.java에서 @Repository로 등록해놓았기 때문에 스프링이 생성해줌
	private MemberDao memberDao;
	
	@Test
	public void testGetTime() throws Exception {
		String time = memberDao.readTime();
		System.out.println("time:" + time);
	}
	
}
