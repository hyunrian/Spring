package com.kh.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.ex01.vo.ProductVo;

@Controller
public class SampleController2 {

	@RequestMapping(value = "/doC", method = RequestMethod.GET)
	public String doC(@ModelAttribute("msg") String msg) { 
		// getparameter를 통해 "msg"로 저장된 값을 얻고 메서드 내에서는 변수명 msg로 사용
		// view까지 자동으로 전송해줌
		System.out.println("msg:" + msg);
		return "doC";
	}
	
	@RequestMapping(value = "/doD", method = RequestMethod.GET)
	public String doD(Model model) {
		// Model : Request보다 크기가 작은 데이터 바구니라고 생각하면 됨
		ProductVo vo = new ProductVo("SampleProduct", 10000);
		model.addAttribute(vo);
		// -> 이름이 없으면 클래스의 첫글자를 소문자로 한 이름으로 저장됨
		// -> doD.jsp에서 ${productVo.name}으로 바로 데이터 얻기 가능
		return "doD";
	}
}
