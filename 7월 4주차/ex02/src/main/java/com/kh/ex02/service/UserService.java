package com.kh.ex02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ex02.dao.UserDao;
import com.kh.ex02.dto.LoginDto;
import com.kh.ex02.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public UserVo login(LoginDto loginDto) {
		return userDao.login(loginDto);
	}
}
