package com.kh.ex02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex02.service.ReplyService;
import com.kh.ex02.vo.ReplyVo;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	// 조회
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public List<ReplyVo> getList(int bno) {
	@RequestMapping(value = "/list/{bno}", method = RequestMethod.GET)
	public List<ReplyVo> getList(@PathVariable("bno") int bno) {
		// @PathVariable : 경로에 변수를 사용하는 경우에 씀(/reply/list/501)
		// @RequestParam : url 뒤에 ?bno=501의 형태로 올 때 사용(/reply/list?bno=501)
		return replyService.getList(bno);
	}
	
	// 입력
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody ReplyVo replyVo) {
		// JSON타입 등으로 들어오는 데이터 -> RequestBody 사용
		replyService.insert(replyVo);
		return "success";
	}
	
	// 수정
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	// RequestMethod를 post나 get으로 해도 동작은 잘 함
	// 부분 업데이트 - patch, 전체 업데이트 - put을 보통 사용함
	public String update(@RequestBody ReplyVo replyVo) {
		replyService.update(replyVo);
		return "success";
	}
	
	// 삭제
	@RequestMapping(value = "/delete/{rno}", method = RequestMethod.DELETE)
	public String delete(@PathVariable int rno) {
		replyService.delete(rno);
		return "success";
	}

}
