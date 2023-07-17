package com.jh.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.ex02.dao.BoardDao;
import com.jh.ex02.vo.BoardVo;
import com.jh.ex02.vo.PagingDto;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	// 입력
	public void insert(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}
	
	// 조회
	public List<BoardVo> selectAll(PagingDto dto) {
		List<BoardVo> list = boardDao.selectAll(dto);
		return list;
	}
	
	// 수정
	public void update(BoardVo boardVo) {
		boardDao.update(boardVo);
	}
	
	// 글 하나 조회
	public BoardVo select1(int bno) {
		return boardDao.select1(bno);
	} 
	
	// 삭제
	public void delete(int bno) {
		boardDao.delete(bno);
	}
	
	public int getTotalCount() {
		int totalCount = boardDao.getTotalCount();
		return totalCount;
	}
	
}
