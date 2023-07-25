package com.kh.ex02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ex02.service.BoardService;
import com.kh.ex02.vo.BoardVo;
import com.kh.ex02.vo.PagingDto;

@Controller
@RequestMapping("/board") 
// 클래스에 전체 경로 지정. 클래스 내 메서드에 지정한 requestmapping은 /board/xxx로 지정됨
public class BoardController {
	
	PagingDto tempDto = null;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	// localhost/board/register
	public void registGet() { // get방식으로 처리
		System.out.println("registGet() called");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	// localhost/board/register
	public String registPost(/*MultipartFile file,*/ BoardVo boardVo, 
			RedirectAttributes rttr) throws Exception { // post방식으로 처리
	// 1. getter&setter가 있고 2. jsp에서 설정한 name과 이름이 같으면 자동으로 boardVo 데이터 처리됨
//		System.out.println("controller, register, file:" + file);
		System.out.println("controller, register, boardVo:" + boardVo);
		// form태그로 파일 넘겨받기
		// model2에서는 파일인 경우와 아닌 경우에 따라 데이터 처리를 따로 설정해야 했지만 
		// spring에서는 자동으로 모두 처리됨
		// jsp에서 넘겨주는 name과 파라미터명이 동일하지 않으면 RequestParam 설정 필요
//		System.out.println("controller, register, originalName:" 
//					+ file.getOriginalFilename()); // 파일명
//		System.out.println("controller, register, name:" 
//				+ file.getName()); // jsp에서 지정한 name
//		System.out.println("controller, register, size:" 
//				+ file.getSize());
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
		
		int count = boardService.getCount(pagingDto);
		
//		System.out.println("pagingDto:" + pagingDto);
		pagingDto = new PagingDto(
				pagingDto.getPage(), pagingDto.getPerPage(), count, 
				pagingDto.getSearchType(), pagingDto.getKeyword());
		tempDto = pagingDto;
//		System.out.println("pagingDto:" + pagingDto);
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
//		System.out.println("boardVo:" + boardVo);
//		System.out.println("pagingDto in read:" + tempDto);
		model.addAttribute("pagingDto", tempDto);
		return "board/read";
	}
	
	// 수정
	@RequestMapping(value = "/mod", method = RequestMethod.POST)
	public String update(BoardVo boardVo) throws Exception {
		boardService.update(boardVo);
		return "redirect:/board/read?bno=" + boardVo.getBno();
	}
	
	// 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int bno) throws Exception {
//		System.out.println("pagingDto in delete:" + tempDto);
		boardService.delete(bno);
		if (tempDto != null) {
			return "redirect:/board/list?page=" + tempDto.getPage() 
			+ "&perPage=" + tempDto.getPerPage()
			+ "&searchType=" + tempDto.getSearchType()
			+ "&keyword=" + tempDto.getKeyword();
		} else {
			return "board/list";
		}
	}
	
	// 글 상세보기 페이지에서 첨부파일명 보이게 하기
	@ResponseBody
	@RequestMapping(value = "/getAttachList/{bno}", method = RequestMethod.GET)
	public List<String> getAttachList(@PathVariable int bno) {
		List<String> list = boardService.getAttachList(bno);
		return list;
	}

}
