package com.kh.ex02.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex02.commons.MyConstants;
import com.kh.ex02.service.ReplyService;
import com.kh.ex02.vo.ReplyVo;
import com.kh.ex02.vo.UserVo;

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
		// 뒤에 오는 ("bno")는 값이 하나인 경우 생략 가능하지만 여러개인 경우 명시해줘야 함
		// @RequestParam : url 뒤에 ?bno=501의 형태로 올 때 사용(/reply/list?bno=501)
		return replyService.getList(bno);
	}
	
	// 입력
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody ReplyVo replyVo, HttpSession session) {
		// JSON타입 등으로 들어오는 데이터 -> RequestBody 사용
		UserVo userVo = (UserVo)session.getAttribute(MyConstants.LOGIN);
		replyVo.setReplyer(userVo.getU_id());
		
		replyService.insert(replyVo);
		return MyConstants.LOGIN;
	}
	
	// 수정
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	// RequestMethod를 post나 get으로 해도 동작은 잘 함
	// 부분 업데이트 - patch, 전체 업데이트 - put을 보통 사용함
	public String update(@RequestBody ReplyVo replyVo, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("loginInfo");
		replyVo.setReplyer(userVo.getU_id());
		replyService.update(replyVo);
		return MyConstants.LOGIN;
	}
	
	// 삭제
	@RequestMapping(value = "/delete/{rno}/{bno}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("rno") int rno, 
						@PathVariable("bno") int bno) {
//		System.out.printf("rno:%d, bno:%d\n", rno, bno);
		replyService.delete(rno, bno);
		return MyConstants.LOGIN;
	}

}
