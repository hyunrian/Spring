package com.jh.ex02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jh.ex02.service.BoardService;
import com.jh.ex02.vo.BoardVo;
import com.jh.ex02.vo.PagingDto;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String selectAll(PagingDto dto, Model model) {
		int totalCount = boardService.getTotalCount();
		dto.setTotalCount(totalCount);
		System.out.println("dto1:" + dto);
		dto = new PagingDto(dto.getNowPage(), dto.getPerPage(), totalCount);
		System.out.println("dto2:" + dto);
		List<BoardVo> list = boardService.selectAll(dto);
		model.addAttribute("list", list);
		model.addAttribute("dto", dto);
		return "board/list";
	}
	
	// 입력
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVo boardVo) {
		boardService.insert(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "board/insert";
	}
	
	// 글 상세보기
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String showDetails(int bno, Model model) {
		BoardVo boardVo = boardService.select1(bno);
		model.addAttribute("boardVo", boardVo);
		System.out.println("vo:" + boardVo);
		return "board/detail";
	}
	
	// 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVo boardVo) {
		System.out.println("vo:" + boardVo);
		boardService.update(boardVo);
		return "redirect:/board/detail?bno=" + boardVo.getBno();
	}
	
	// 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int bno) {
		System.out.println("bno:" + bno);
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
}
