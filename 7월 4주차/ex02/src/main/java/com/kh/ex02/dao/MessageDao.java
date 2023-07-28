package com.kh.ex02.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ex02.vo.MessageVo;

@Repository
public class MessageDao {
	
	private final String NAMESPACE = "com.kh.ex02.MessageMapper.";
	
	@Autowired
	SqlSession sqlSession;

	// 생성(메시지 전송)
	public void create(MessageVo messageVo) {
		sqlSession.insert(NAMESPACE + "create", messageVo);
	}
	
	// 읽기
	public MessageVo readMessage(int m_id) {
		return sqlSession.selectOne(NAMESPACE + "readMessage", m_id);
	}
	
	// 메시지 읽은 날짜 설정
	public void updateState(int m_id) {
		sqlSession.update(NAMESPACE + "updateState", m_id);
	}
	
	// 읽지 않은 메시지 개수 확인
	public int countUnreadMessage(String targetid) {
		return sqlSession.selectOne(NAMESPACE + "countUnreadMessage", targetid);
	}
	
}
