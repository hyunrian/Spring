package com.kh.ex02.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ex02.vo.ReplyVo;

@Repository
public class ReplyDao {
	
	private final String NAMESPACE = "com.kh.ex02.ReplyMapper.";

	@Autowired
	SqlSession sqlSession;
	
	// 조회
	public List<ReplyVo> getList(int bno) {
		return sqlSession.selectList(NAMESPACE + "list", bno);
	}
	
	// 입력
	public void insert(ReplyVo replyVo) {
		sqlSession.insert(NAMESPACE + "insert", replyVo);
	}
	
	// 수정
	public void update(ReplyVo replyVo) {
		sqlSession.update(NAMESPACE + "update", replyVo);
	}
	
	// 삭제
	public void delete(int rno) {
		sqlSession.delete(NAMESPACE + "delete", rno);
	}
	
}
