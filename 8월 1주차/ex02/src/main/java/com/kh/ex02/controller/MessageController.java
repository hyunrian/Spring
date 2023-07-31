package com.kh.ex02.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex02.commons.MyConstants;
import com.kh.ex02.service.MessageService;
import com.kh.ex02.vo.MessageVo;
import com.kh.ex02.vo.UserVo;

@RestController
@RequestMapping("/message")
public class MessageController {
	
//	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(MessageVo messageVo, HttpSession session) {
		
//		logger.info("{}", messageVo);
		
		UserVo userVo = (UserVo)session.getAttribute(MyConstants.LOGIN);
		messageVo.setSender(userVo.getU_id());
		messageService.SendMessage(messageVo);
		userVo.setU_point(userVo.getU_point() + MyConstants.SEND_MESSAGE_POINT);
		// header.jsp에서 point를 세션에 저장된 loginInfo의 값을 가져오고 있기 때문에
		// userVo 값을 재설정 해줘야 함
		return MyConstants.SUCCESS_MESSAGE;
	}
	
	// 읽지 않은 메시지 개수 확인 - 여기부터 해야 함!! 
	@RequestMapping(value = "/unread", method = RequestMethod.GET)
	public int countUnreadMessage(HttpSession session) {
//		UserVo userVo = (UserVo)session.getAttribute("loginInfo"); // -> MyConstants로 하면 값을 못가져옴
		UserVo userVo = (UserVo)session.getAttribute(MyConstants.LOGIN); 
		if (userVo != null) {
//			System.out.println("user: " + userVo);
			return messageService.countUnreadMessage(userVo.getU_id());
		}
		return 0;
	}
	
}
