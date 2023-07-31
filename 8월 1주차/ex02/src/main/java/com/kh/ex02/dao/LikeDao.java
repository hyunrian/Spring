package com.kh.ex02.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ex02.vo.LikeVo;

@Repository
public class LikeDao {

	@Autowired
	SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.kh.ex02.LikeMapper.";
	
	public void likeContent(LikeVo likeVo) {
		sqlSession.insert(NAMESPACE + "likeContent", likeVo);
	}
	
	public int isLiked(LikeVo likeVo) {
		return sqlSession.selectOne(NAMESPACE + "isLiked", likeVo);
	}
	
	public int countLikes(int bno) {
		return sqlSession.selectOne(NAMESPACE + "countLikes", bno);
	}
	
	public void dislikeContent(LikeVo likeVo) {
		sqlSession.delete(NAMESPACE + "dislikeContent", likeVo);
	}
	
}
