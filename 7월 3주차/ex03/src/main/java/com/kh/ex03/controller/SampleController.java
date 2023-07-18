package com.kh.ex03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex03.vo.SampleVo;

@RestController 
// @RestController: 클래스 내 모든 메서드에 @ResponseBody가 자동으로 설정됨
// ==> forward나 redirect가 아니라 데이터 자체로 응답함
// ==> 자바 객체인 경우에는 JSON 형태로 변환해서 응답함
// ==> jackson-databind 라이브러리가 필요함
// 화면(view)가 필요한 것이 아니라, 주로 비동기(Ajax)처리 또는 웹브라우저가 아닌 다른 기기를 위한 컨트롤러로 사용됨
public class SampleController {

	// 스트링 반환
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello"; 
		// hello.jsp로 forward하는 것이 아니라 "hello"라는 텍스트로 응답함
	}
	
	// 객체 반환
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public SampleVo json() {
		SampleVo vo = new SampleVo();
		vo.setName("아이폰");
		vo.setPrice(1000000);
		return vo;
	}
	
	// 객체 리스트 반환
	@RequestMapping(value = "/json-list", method = RequestMethod.GET)
	public List<SampleVo> jsonList() {
		List<SampleVo> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			SampleVo vo = new SampleVo();
			vo.setName("버즈-" + i);
			vo.setPrice(10000 + i);
			list.add(vo);
		}
		return list;
	}
	
	//ResponseEntity 사용해보기 (코드 변경)
	/*
	@RequestMapping(value = "/json-list", method = RequestMethod.GET)
	public ResponseEntity<List<SampleVo>> jsonList() {
		List<SampleVo> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			SampleVo vo = new SampleVo();
			vo.setName("버즈-" + i);
			vo.setPrice(10000 + i);
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVo>>(list, HttpStatus.NOT_FOUND);
	}
	*/
	
	
	
}
