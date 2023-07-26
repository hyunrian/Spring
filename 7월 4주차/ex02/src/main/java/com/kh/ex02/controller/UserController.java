package com.kh.ex02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ex02.dto.LoginDto;
import com.kh.ex02.service.UserService;
import com.kh.ex02.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	// 로그인 폼
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginForm() {
//	public String loginForm() {
//		return "/board/login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginRun(LoginDto loginDto, HttpSession session, 
					RedirectAttributes rttr) {
		System.out.println("loginDto:" + loginDto);
		UserVo userVo = userService.login(loginDto);
		String returnPage = "redirect:/board/list";
		if (userVo != null) {
			session.setAttribute("userVo", userVo);
		} else {
			rttr.addFlashAttribute("loginResult", "FAIL");
			returnPage = "redirect:/user/login";
		}
		return returnPage;
	}
	
	
}
