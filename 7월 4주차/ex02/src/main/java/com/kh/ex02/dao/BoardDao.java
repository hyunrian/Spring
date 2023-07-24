package com.kh.ex02.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ex02.vo.BoardVo;
import com.kh.ex02.vo.PagingDto;

@Repository
public class BoardDao {
	
	private final String NAMESPACE = "com.kh.ex02.BoardMapper.";

	@Autowired
	private SqlSession sqlSession;
	
	public void create(BoardVo boardVo) throws Exception {
		sqlSession.insert(NAMESPACE + "create", boardVo);
	}

	public List<BoardVo> listAll(PagingDto pagingDto) throws Exception {
		List<BoardVo> list = sqlSession.selectList(
				NAMESPACE + "listAll", pagingDto);
		return list;
	}

	public BoardVo read(int bno) throws Exception {
		BoardVo boardVo = sqlSession.selectOne(NAMESPACE + "read", bno);
		return boardVo;
	}

	public void update(BoardVo boardVo) throws Exception {
		sqlSession.update(NAMESPACE + "update", boardVo);
	}

	public void delete(int bno) throws Exception {
		sqlSession.delete(NAMESPACE + "delete", bno);
	}
	
	public int getCount(PagingDto pagingDto) throws Exception {
		int count = sqlSession.selectOne(NAMESPACE + "getCount", pagingDto);
		return count;
	}
	
	public void updateReplycnt(int bno, int amount) {
		Map<String, Integer> map = new HashMap<>();
		map.put("amount", amount);
		map.put("bno", bno);
		sqlSession.update(NAMESPACE + "updateReplycnt", map);
	}

}
