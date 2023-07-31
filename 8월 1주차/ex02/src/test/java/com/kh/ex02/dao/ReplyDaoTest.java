package com.kh.ex02.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.ex02.vo.ReplyVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyDaoTest {

	@Autowired
	private ReplyDao replyDao;
	
	@Test
	public void testInsert() {
		ReplyVo vo = new ReplyVo();
		vo.setBno(501);
		vo.setReplytext("괌 가야지");
		vo.setReplyer("우끼");
		replyDao.insert(vo);
	}
	
	@Test
	public void testGetList() {
		List<ReplyVo> list = replyDao.getList(501);
		System.out.println(list);
	}
	
	@Test
	public void testUpdate() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(1);
		vo.setReplytext("댓글 테스트 수정");
		vo.setReplyer("끼끼 수정");
		replyDao.update(vo);
	}
	
	@Test
	public void testDelete() {
		replyDao.delete(1);
	}
}
