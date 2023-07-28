package com.kh.ex02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex02.commons.MyConstants;
import com.kh.ex02.service.LikeService;
import com.kh.ex02.vo.LikeVo;
import com.kh.ex02.vo.UserVo;

@RestController
@RequestMapping("/like")
public class LikeController {

	@Autowired
	LikeService likeService;
	
	@RequestMapping(value = "/heart", method = RequestMethod.POST)
	public int likeContent(int bno, HttpSession session) {
//		System.out.println("bno: " + bno);
		UserVo userVo = (UserVo)session.getAttribute(MyConstants.LOGIN);
		LikeVo likeVo = new LikeVo(userVo.getU_id(), bno);
		System.out.println("likeVo:" + likeVo);
		int userLike = likeService.isLiked(likeVo);
		if (userLike != 1) {
			System.out.println("like 실행");
			likeService.likeContent(likeVo);
		}
		int count = likeService.countLikes(bno);
		return count;
	}
	
	@RequestMapping(value = "/isLiked/{bno}", method = RequestMethod.POST)
	public int isLiked(@PathVariable int bno, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute(MyConstants.LOGIN);
		int userLike = likeService.isLiked(new LikeVo(userVo.getU_id(), bno));
		System.out.println("userLike:" + userLike);
		return userLike;
	}
	
	@RequestMapping(value = "/count/{bno}", method = RequestMethod.POST)
	public int countLikes(@PathVariable int bno) {
		return likeService.countLikes(bno);
	}
	
	@RequestMapping(value = "/dislike/{bno}", method = RequestMethod.DELETE)
	public int dislikeContent(@PathVariable int bno, HttpSession session) {
		System.out.println("dislike 실행");
		UserVo userVo = (UserVo)session.getAttribute(MyConstants.LOGIN);
		LikeVo likeVo = new LikeVo(userVo.getU_id(), bno);
		System.out.println("likeVo:" + likeVo);
		likeService.dislikeContent(likeVo);
		int count = likeService.countLikes(bno);
		return count;
	}
	
}
