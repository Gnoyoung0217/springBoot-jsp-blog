package com.jerryLog.www.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerryLog.www.Model.UserBean;

// 참조 경로 >> https://astrid-dm.tistory.com/536
// @SpringBootTest
//  : 통합 테스트를 위한 SpringBootTest 어노테이션 설정
//  : 모든 Bean을 로드하기때문에 운영환경과 가장 유사한 테스트가 가능
// @AutoConfigureMockMvc
//  : MockMvc를 이용하기 위한 어노테이션
//  : @MockBean 선언된 객체 있을경우, 해당 객체로 Bean 생성

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class LoginControllerTest {

	UserBean user;
	
	@Autowired
	private WebApplicationContext context; // MockMvc 객체 생성을 위한 context

	@Autowired
	private MockMvc mvc; // controller에 request를 수행해주는 mock 객체
	
	@BeforeEach
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print())
				.build();
	}

	@Test
	@DisplayName("# Post LoginController.list Test")
	public void testList() throws Exception {

		// ################### START : MOC MVC Test ###################
		 this.mvc.perform(post("/api/users/list"))
				 .andExpect(status().isOk())
				 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				 .andExpect(jsonPath("$.result", is("Success")))
				 .andDo(print());

		// ################### END : MOC MVC Test ####################
	}
	
	@Test
	@DisplayName("# Post LoginController.login Test")
	public void testLogin() throws Exception {
		
		user = new UserBean();
		user.setEmail("ADMIN");
		user.setPassword("123");
		
		// ################### START : MOC MVC Test ###################
		this.mvc.perform(post("/api/users/login")
						.content(new ObjectMapper().writeValueAsString(user))
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.result", is("Success")))
				.andDo(print());
				
		// ################### END : MOC MVC Test ####################
		
	}

}
