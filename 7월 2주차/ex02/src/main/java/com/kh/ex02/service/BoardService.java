package com.kh.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ex02.dao.BoardDao;
import com.kh.ex02.vo.BoardVo;
import com.kh.ex02.vo.PagingDto;

@Service // 서비스에 붙이는 애너테이션
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	public void create(BoardVo boardVo) throws Exception {
		boardDao.create(boardVo);
	}

	public List<BoardVo> listAll(PagingDto pagingDto) throws Exception {
		List<BoardVo> list = boardDao.listAll(pagingDto);
		return list;
	}

	public BoardVo read(int bno) throws Exception {
		BoardVo boardVo = boardDao.read(bno);
		return boardVo;
	}

	public void update(BoardVo boardVo) throws Exception {
		boardDao.update(boardVo);
	}

	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}
	
	public int getCount() throws Exception {
		return boardDao.getCount();
	}

}
