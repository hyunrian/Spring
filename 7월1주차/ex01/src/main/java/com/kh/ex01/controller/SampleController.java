package com.kh.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {

	@RequestMapping(value = "/doA", method = RequestMethod.GET) 
	// get방식으로 /doA라는 요청이 들어오면 실행
	public void doA() {
		System.out.println("doA 실행됨");
//		return "doA"; 
		// /WEB-INF/views/doA.jsp(model2할 때 nextPage forward한 것과 같음)
		// servlet-context.xml에 미리 설정되어 있는 prefix, suffix가 있음
		// 메서드 반환타입이 void인 경우 value로 설정한 값.jsp가 자동으로 실행됨
	}
	
	// 설정한 값과 다르게 경로를 지정하고 싶은 경우 String으로 리턴하면 됨
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
//	@RequestMapping(value = "/doB")
	public String doB() {
		System.out.println("doB 실행됨");
		return "do_B";
	}
	
}
