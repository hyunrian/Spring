package com.kh.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	
	// localhost/doE -> localhost/doF로 redirect -> doF.jsp로 forward
	
	@RequestMapping(value = "/doE", method = RequestMethod.GET)
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "hello");
		// 게시판 실습 때 Controller에서 session.setAttribute("msg", "hello")
		// -> View에서 session.removeAttribute("msg") 했던 것과 같음
		// localhost/doE 로 doF.jsp 화면이 나온 이후 새로고침하면 msg값이 사라짐
		
		return "redirect:/doF";
	}
	
	@RequestMapping(value = "/doF", method = RequestMethod.GET)
	public void doF() { 
		
	}

}
