package com.kh.ex01.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ex01.vo.MemberVo;

@Repository // Spring에게 이 객체가 Dao라는 것을 알림
public class MemberDaoImpl implements MemberDao {
	
	private static String NAMESPACE = "com.kh.ex01.memberMapper.";
	
	@Autowired
	private SqlSession sqlSession; // root-context.xml에 등록해놓음

	@Override
	public String readTime() throws Exception {
		String time = sqlSession.selectOne(NAMESPACE + "getTime"); 
		// 결과가 하나일 때 select"One" 사용
		// com.kh.ex01.memberMapper.getTime과 같음
		
		return time;
	}

	@Override
	public void createMember(MemberVo vo) throws Exception {
		
	}

	@Override
	public MemberVo readMember(String userid) throws Exception {
		
		
		return null;
	}

	@Override
	public MemberVo readWithPw(String userid, String userpw) 
			throws Exception {
		
		
		return null;
	}

	
}
