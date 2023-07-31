package com.kh.ex02.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.ex02.vo.MessageVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MessageServiceTest {
	
	@Autowired
	private MessageService messageService;
	
	@Test
	public void testSendMessage() {
		// user01 -> user02 : "hello"
		MessageVo messageVo = new MessageVo();
		messageVo.setSender("user01");
		messageVo.setTargetid("user02");
		messageVo.setMessage("hello2");
		messageService.SendMessage(messageVo);
	}
	
	@Test
	public void testReadMessage() {
		MessageVo messageVo = new MessageVo();
		messageVo.setM_id(1);
		messageVo.setTargetid("user02");
		messageVo = messageService.readMessage(messageVo);
		System.out.println(messageVo);
	}

}
