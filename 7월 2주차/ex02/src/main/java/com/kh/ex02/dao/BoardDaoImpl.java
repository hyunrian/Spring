package com.kh.ex02.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ex02.vo.BoardVo;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private final String NAMESPACE = "com.kh.ex02.BoardMapper.";

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void create(BoardVo boardVo) throws Exception {
		sqlSession.insert(NAMESPACE + "create", boardVo);
	}

	@Override
	public List<BoardVo> listAll() throws Exception {
		List<BoardVo> list = sqlSession.selectList(NAMESPACE + "listAll");
		return list;
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		BoardVo boardVo = sqlSession.selectOne(NAMESPACE + "read", bno);
		return boardVo;
	}

	@Override
	public void update(BoardVo boardVo) throws Exception {
		sqlSession.update(NAMESPACE + "update", boardVo);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(NAMESPACE + "delete", bno);
	}

}
