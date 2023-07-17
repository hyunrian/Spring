package com.jh.ex02.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jh.ex02.dao.BoardDao;
import com.jh.ex02.vo.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDaoTest {

	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void testInsert() throws Exception {
		BoardVo vo = new BoardVo();
		for (int i = 1; i <= 103; i++) {
			vo.setTitle("title" + i);
			vo.setContent("content-" + i);
			vo.setWriter("writer-" + i);
			boardDao.insert(vo);
		}
	}
	
	@Test
	public void testSelectAll() throws Exception {
		List<BoardVo> list = boardDao.selectAll();
		System.out.println("dao: " + list);
	}
	
	@Test
	public void testUpdate() throws Exception {
		BoardVo vo = boardDao.select1(2);
		vo.setTitle("반지의 제왕");
		vo.setContent("판타지");
		vo.setWriter("film");
		boardDao.update(vo);
	}
	
	@Test
	public void testDelete() {
		boardDao.delete(4);
	}
	
}
