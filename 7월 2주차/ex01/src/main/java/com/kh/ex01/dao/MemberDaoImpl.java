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
		int count = sqlSession.insert(NAMESPACE + "insertMember", vo);
		// vo를 memverMapper.xml에 넘겨줘야함
		// memberMapper.xml에 넘겨줘야할 파라미터타입이 있는 경우 insert(string, object) 사용
		System.out.println("count:" + count);
	}

	@Override
	public MemberVo readMember(String userid) throws Exception {
		MemberVo memberVo = sqlSession.selectOne(
				NAMESPACE + "selectMember", userid);
		
		return memberVo;
	}

	@Override
	public MemberVo readWithPw(String userid, String userpw) 
			throws Exception {
		MemberVo memberVo = new MemberVo();
		memberVo.setUserid(userid);
		memberVo.setUserpw(userpw);
		memberVo = sqlSession.selectOne(
				NAMESPACE + "selectMemberWithPw", memberVo);
		return memberVo;
	}

	
}
