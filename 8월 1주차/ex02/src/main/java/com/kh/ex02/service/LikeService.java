package com.kh.ex02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ex02.dao.LikeDao;
import com.kh.ex02.vo.LikeVo;

@Service
public class LikeService {

	@Autowired
	LikeDao likeDao;
	
	public void likeContent(LikeVo likeVo) {
		likeDao.likeContent(likeVo);
	}
	
	public int isLiked(LikeVo likeVo) {
		return likeDao.isLiked(likeVo);
	}
	
	public int countLikes(int bno) {
		return likeDao.countLikes(bno);
	}
	
	public void dislikeContent(LikeVo likeVo) {
		likeDao.dislikeContent(likeVo);
	}
	
}
