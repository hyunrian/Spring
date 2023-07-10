package com.kh.ex01.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ex01.vo.ProductVo;

@Controller
public class SampleController5 {

	// JSONObject와 같은 기능
	// localhost/doJSON
	@RequestMapping(value = "/doJSON", method = RequestMethod.GET)
	@ResponseBody // JSON형식 사용을 위한 애너테이션
	public ProductVo doJSON() {
		ProductVo vo = new ProductVo("샘플 상품", 30000);
		return vo; 
		// 그냥 return하면 원래 사용하던 데이터 리턴,
		// ResponseBody를 사용하면 JSON 타입으로 리턴함
	}
	
	// JSONArray와 같은 기능
	// localhost/doJSONList
	@RequestMapping(value = "/doJSONList", method = RequestMethod.GET)
	@ResponseBody 
	public List<ProductVo> doJSONList() {
		List<ProductVo> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			ProductVo vo = new ProductVo("샘플 상품 - " + i, (i * 10000));
			list.add(vo);
		}
		return list; 
	}
	
}
