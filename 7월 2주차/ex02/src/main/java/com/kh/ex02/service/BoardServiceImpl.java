package com.kh.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ex02.dao.BoardDao;
import com.kh.ex02.vo.BoardVo;

@Service // 서비스에 붙이는 애너테이션
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public void create(BoardVo boardVo) throws Exception {
		boardDao.create(boardVo);
	}

	@Override
	public List<BoardVo> listAll() throws Exception {
		List<BoardVo> list = boardDao.listAll();
		return list;
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		BoardVo boardVo = boardDao.read(bno);
		return boardVo;
	}

	@Override
	public void update(BoardVo boardVo) throws Exception {
		boardDao.update(boardVo);
	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

}
