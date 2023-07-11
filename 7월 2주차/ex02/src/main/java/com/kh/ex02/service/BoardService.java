package com.kh.ex02.service;

import java.util.List;

import com.kh.ex02.vo.BoardVo;

public interface BoardService {

	// 글 작성
	public void create(BoardVo boardVo) throws Exception;
	
	// 글 전체 조회
	public List<BoardVo> listAll() throws Exception;
	
	// 특정 글 1개 조회
	public BoardVo read(int bno) throws Exception; 
	
	// 글 수정
	public void update(BoardVo boardVo) throws Exception;
	
	// 글 삭제
	public void delete(int bno) throws Exception;
	
}
