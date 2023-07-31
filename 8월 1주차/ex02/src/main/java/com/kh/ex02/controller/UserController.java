package com.kh.ex02.controller;

import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ex02.dto.LoginDto;
import com.kh.ex02.service.UserService;
import com.kh.ex02.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JavaMailSenderImpl mailSender; // 빈에 등록한 메일 보내기 라이브러리

	@Autowired
	private UserService userService;
	
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
//		System.out.println("loginDto:" + loginDto);
		UserVo userVo = userService.login(loginDto);
		String returnPage = "redirect:/board/list";
		
		if (userVo != null) {
			String targetLocation = (String)session.getAttribute("targetLocation");
			if (targetLocation != null) {
				returnPage = "redirect:" + targetLocation;
			}
			session.removeAttribute("targetLocation");
			session.setAttribute("loginInfo", userVo);
		} else {
			rttr.addFlashAttribute("loginResult", "FAIL");
			returnPage = "redirect:/user/login";
		}
		return returnPage;
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); // 현재 세션 무효화
		return "redirect:/user/login";
	}
	
	// 비밀번호 찾기
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword() {
		return "/user/forgotPassword";
	}
	
	// 비밀번호 이메일 전송
	@RequestMapping(value = "/sendPassword", method = RequestMethod.POST)
	public String sendPassword(String id, String email, RedirectAttributes rttr) {
		// 원래는 id에 등록된 email이 맞는지 검증 필요. 지금은 생략
		System.out.println("id: " + id);
		System.out.println("email: " + email);
		
		// 비밀번호 새로 생성 -> 테이블에 넣기 -> 사용자에게 이메일 전송
		String uuid = UUID.randomUUID().toString();
		String newPw = uuid.substring(0, uuid.indexOf("-"));
		System.out.println("newPW: " + newPw);
		rttr.addFlashAttribute("sentPassword", "sentPassword");
		
		// 메일을 보내기 전 필요한 세팅(받는사람, 내용 등)
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(
						mimeMessage,
						false, // multipart로 보낼지 여부(파일 첨부)
						"utf-8" // 보내는 인코딩 방식
						);
				helper.setFrom("jihahyun21@gmail.com"); // 보내는 사람
				helper.setTo(email); // 받는 사람
				helper.setSubject("임시 비밀번호 발급"); // 메일 제목
				helper.setText("임시 비밀번호 : " + newPw); // 메일 내용
			}
		};
		mailSender.send(preparator);
		
		return "redirect:/user/login";
	}
	
	
}
