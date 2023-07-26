package com.kh.ex02;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ex02.vo.BoardVo;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		return "index";
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody 
	// ResponseBody: jsp로 forward하거나 redirect하는 것이 아니라, 데이터 자체로 응답함
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	@ResponseBody 
	public String hello2(BoardVo boardVo) {
		System.out.println("boardVo:" + boardVo);
		return "hello";
	}
	
	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	@ResponseBody
	public String doA() {
		System.out.println("doA run");
		return "doA";
	}
	
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
	@ResponseBody
	public String doB() {
		System.out.println("doB run");
		return "doB";
	}
	
	
}
