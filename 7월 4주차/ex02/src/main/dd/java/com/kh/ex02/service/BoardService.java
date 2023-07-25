package com.kh.ex02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex02.dao.BoardDao;
import com.kh.ex02.vo.BoardVo;
import com.kh.ex02.vo.PagingDto;

@Service // 서비스에 붙이는 애너테이션
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Transactional
	public void create(BoardVo boardVo) throws Exception {
		int bno = boardDao.getNextSeq();
		boardVo.setBno(bno);
		boardDao.create(boardVo); // tbl_board에 추가
		
		for (String filename : boardVo.getFiles()) {
			boardDao.insertAttach(filename, bno); // tbl_attach에 추가
		}
	}

	public List<BoardVo> listAll(PagingDto pagingDto) throws Exception {
		List<BoardVo> list = boardDao.listAll(pagingDto);
		return list;
	}

	@Transactional
	public BoardVo read(int bno) throws Exception {
		BoardVo boardVo = boardDao.read(bno);
		boardDao.updateViewCnt(bno);
		return boardVo;
	}

	public void update(BoardVo boardVo) throws Exception {
		boardDao.update(boardVo);
	}

	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}
	
	public int getCount(PagingDto pagingDto) throws Exception {
		return boardDao.getCount(pagingDto);
	}
	
	public List<String> getAttachList(int bno) {
		return boardDao.getAttachList(bno);
	}

}
