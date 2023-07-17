package com.jh.ex02.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jh.ex02.vo.BoardVo;
import com.jh.ex02.vo.PagingDto;

@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	public void insert(BoardVo boardVo) {
		sqlSession.insert("myMapper.insert", boardVo);
	}
	
	public List<BoardVo> selectAll(PagingDto dto) {
		List<BoardVo> list = sqlSession.selectList("myMapper.selectAll", dto);
		return list;
	}
	
	public int getTotalCount() {
		int totalCount = sqlSession.selectOne("myMapper.getTotalCount");
		return totalCount;
	}
	
	public void update(BoardVo boardVo) {
		sqlSession.update("myMapper.update", boardVo);
	}
	
	public BoardVo select1(int bno) {
		BoardVo boardVo = sqlSession.selectOne("myMapper.select1", bno);
		return boardVo;
	}
	
	public void delete(int bno) {
		sqlSession.delete("myMapper.delete", bno);
	}
}
