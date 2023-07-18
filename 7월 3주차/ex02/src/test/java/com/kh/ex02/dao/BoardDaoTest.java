package com.kh.ex02.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.ex02.vo.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDaoTest {

	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void testCreate() throws Exception {
		BoardVo vo = new BoardVo();
		vo.setTitle("제목-1");
		vo.setContent("내용-1");
		vo.setWriter("홍길동");
		boardDao.create(vo);
	} 
	
	@Test
	public void testCreate500() throws Exception {
		for (int i = 1; i <= 500; i++) {
			BoardVo vo = new BoardVo();
			vo.setTitle("제목-" + i);
			vo.setContent("내용-" + i);
			vo.setWriter("작성자-" + i);
			boardDao.create(vo);
			System.out.println(vo);
			Thread.sleep(100);
		} 
	}
	
//	@Test
//	public void testListAll() throws Exception {
//		List<BoardVo> list = boardDao.listAll();
//		for (BoardVo boardVo : list) {
//			System.out.println(boardVo);
//		}
//	}
	
	@Test
	public void testRead() throws Exception {
		boardDao.read(3);
	}
	
	@Test
	public void testUpdate() throws Exception {
		BoardVo vo = boardDao.read(1);
		vo.setTitle("제목-1-수정");
		vo.setWriter("크리스마스");
		boardDao.update(vo);
	}
	
	@Test
	public void testDelete() throws Exception {
		boardDao.delete(10);
	}
	
}
