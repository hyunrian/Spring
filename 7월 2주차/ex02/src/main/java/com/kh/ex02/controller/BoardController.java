package com.kh.ex02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ex02.service.BoardService;
import com.kh.ex02.vo.BoardVo;
import com.kh.ex02.vo.PagingDto;

@Controller
@RequestMapping("/board") 
// 클래스에 전체 경로 지정. 클래스 내 메서드에 지정한 requestmapping은 /board/xxx로 지정됨
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	// localhost/board/register
	public void registGet() { // get방식으로 처리
		System.out.println("registGet() called");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	// localhost/board/register
	public String registPost(BoardVo boardVo, 
			RedirectAttributes rttr) throws Exception { // post방식으로 처리
	// 1. getter&setter가 있고 2. jsp에서 설정한 name과 이름이 같으면 자동으로 boardVo 데이터 처리됨
		
		System.out.println(boardVo);
		boardService.create(boardVo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
//			@RequestParam(value = "page", defaultValue = "1") int page, 
			PagingDto pagingDto, Model model) throws Exception { 
		// @RequestParam은 생략해도 가능. 가독성을 위해 사용
		// value값이 넘어오지 않았다면 defaultValue 적용
		
		int count = boardService.getCount();
		
		pagingDto = new PagingDto(
				pagingDto.getPage(), pagingDto.getPerPage(), count);
		System.out.println("pagingDto:" + pagingDto);
		List<BoardVo> list = boardService.listAll(pagingDto);
		model.addAttribute("list", list);
		model.addAttribute("pagingDto", pagingDto);
		return "board/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(int bno, 
			Model model) throws Exception { // spring이 파라미터 값 자동으로 가져와줌 
		BoardVo boardVo = boardService.read(bno);
		model.addAttribute("boardVo", boardVo);
		return "board/read";
	}
	
	@RequestMapping(value = "/mod", method = RequestMethod.POST)
	public String update(BoardVo boardVo) throws Exception {
		boardService.update(boardVo);
		return "redirect:/board/read?bno=" + boardVo.getBno();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int bno) throws Exception {
		boardService.delete(bno);
		return "redirect:/board/list";
	}

}
