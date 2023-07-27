package com.kh.ex02.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex02.dao.BoardDao;
import com.kh.ex02.dto.PagingDto;
import com.kh.ex02.util.FileuploadUtil;
import com.kh.ex02.vo.BoardVo;

@Service // 서비스에 붙이는 애너테이션
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "uploadPath")
	private String uploadPath;

	@Transactional
	public void create(BoardVo boardVo) throws Exception {
		int bno = boardDao.getNextSeq();
		boardVo.setBno(bno);
		boardDao.create(boardVo); // tbl_board에 추가
		if (boardVo.getFiles() != null) {
			for (String filename : boardVo.getFiles()) {
				boardDao.insertAttach(filename, bno); // tbl_attach에 추가
			}
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

	@Transactional
	public void update(BoardVo boardVo, String u_id) throws Exception {
		boardDao.update(boardVo, u_id);
		if (boardVo.getFiles() != null) {
			for (String fullname : boardVo.getFiles()) {
				boardDao.insertAttach(fullname, boardVo.getBno());
			}
		}
	}

	@Transactional
	public void delete(int bno) throws Exception {
		List<String> fullnames = boardDao.getAttachList(bno);
		for (String filename : fullnames) {
			boardDao.deleteAttach(filename);
			FileuploadUtil.deleteFile(uploadPath, filename);
		}
		boardDao.delete(bno);
	}
	
	public int getCount(PagingDto pagingDto) throws Exception {
		return boardDao.getCount(pagingDto);
	}
	
	public List<String> getAttachList(int bno) {
		return boardDao.getAttachList(bno);
	}
	
	// 첨부파일 데이터 삭제
	public void deleteAttach(String filename) {
		boardDao.deleteAttach(filename);
	}
}
