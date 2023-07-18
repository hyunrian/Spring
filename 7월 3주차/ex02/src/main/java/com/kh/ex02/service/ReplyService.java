package com.kh.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ex02.dao.ReplyDao;
import com.kh.ex02.vo.ReplyVo;

@Service
public class ReplyService {
	
	@Autowired
	ReplyDao replyDao;
	
	// 조회
	public List<ReplyVo> getList(int bno) {
		return replyDao.getList(bno);
	}
	
	// 입력
	public void insert(ReplyVo replyVo) {
		replyDao.insert(replyVo);
	}
	
	// 수정
	public void update(ReplyVo replyVo) {
		replyDao.update(replyVo);
	}
	
	// 삭제
	public void delete(int rno) {
		replyDao.delete(rno);
	}

}
