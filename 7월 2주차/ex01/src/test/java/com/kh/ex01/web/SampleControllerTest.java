package com.kh.ex01.web;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// 컨트롤러 테스트하기(톰캣보다 간단한 스프링에서 지원하는 테스트용 서버 사용)

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // 환경설정
@ContextConfiguration(locations = 
	{"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) // 읽을 설정파일 설정
public class SampleControllerTest {

	@Inject 
	// 자바에서 지원하는 자동 주입 애너테이션
	// 스프링에서는 @Autowired를 제공(같은 기능이므로 둘 중 아무거나 사용 가능)
	// 객체 생성을 직접 하지 않아도 스프링이 자동으로 생성함
	private WebApplicationContext wac;
	
	// 테스트를 위한 웹 서버 역할
	private MockMvc mockMvc;
	
	@Before // @Before : 테스트를 진행하기 전 해야 할 사전작업 작성
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		System.out.println("mockMvc: " + mockMvc);
		// mockMvc로 테스트를 하겠다는 설정
	}
	
	@Test // @Test : 테스트를 하려면 작성해야 하는 애너테이션
	public void testWac() {
		System.out.println("wac: " + wac);
	} // 반드시 public, void여야 하며 파라미터가 있으면 안됨
	
	@Test
	public void testDoA() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/doA")); 
		// get방식이면 get, post방식이면 post
	}
	
}
