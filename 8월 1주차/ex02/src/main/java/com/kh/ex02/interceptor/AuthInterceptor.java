package com.kh.ex02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.ex02.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(); // 세션 - 로그아웃에 준하는 경우가 일어나기 전까지 서버에 데이터 저장
		UserVo loginInfo = (UserVo)session.getAttribute("loginInfo");
		
		if (loginInfo == null) { // 로그인되어있지 않은 경우
			
			String uri = request.getRequestURI(); // 요청 대상 uri 얻기
//			System.out.println("uri: " + uri); 
			String queryString = request.getQueryString(); // queryString 얻기
//			System.out.println("queryString: " + queryString); 
			String targetLocation = uri + "?" + queryString;
			session.setAttribute("targetLocation", targetLocation);
			response.sendRedirect("/user/login"); // 로그인페이지로 이동
			
			return false; // 실제 요청 처리를 하지 않음(/board/register 등 요청에 대한 처리를 하지 않음)
		}
		
		return true; // 로그인되어 있으면 true 반환(/board/register 등 요청에 대한 처리를 수행함)
	}
}
