package com.kh.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {

	@RequestMapping(value = "/doA", method = RequestMethod.GET) 
	// get방식으로 /doA라는 요청이 들어오면 실행
	public String doA() {
		System.out.println("doA 실행됨");
		return "doA"; // /WEB-INF/views/doA.jsp(model2할 때 nextPage forward한 것과 같음)
	}
	
}
