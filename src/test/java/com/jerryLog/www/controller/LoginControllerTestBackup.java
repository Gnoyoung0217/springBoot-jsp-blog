//package com.jerryLog.www.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.sql.Timestamp;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jerryLog.www.bean.UserBean;
//import com.jerryLog.www.controller.LoginController;
//import com.jerryLog.www.service.LoginService;
//
//
//// 참조자료 https://gaemi606.tistory.com/entry/JUnit-5-WebMvcTest%EC%99%80-SpringBootTest
//// Controller용 JUnit Test
//@WebMvcTest(LoginController.class) // WebLayer만 테스트할때에 사용하며, 클래스 지정 시 특정 컨트롤러만 인스턴스화 하여 테스트하는것도 가능
//@WebAppConfiguration // WebApplicationContext를 생성할 수 있도록 하는 어노테이션
//public class LoginControllerTestBackup {
//	
//	private WebApplicationContext context; 	// MockMvc 객체 생성을 위한 context
//	private MockMvc mockMvc;				// controller에 request를 수행해주는 mock 객체
//	ObjectMapper mapper;					// 객체를 json 형식으로 변경시 사용
//	
//	@Autowired
//	LoginController loginController; // 테스트를 진행할 controller
//	
//	@MockBean
//	LoginService loginService;		 // Controller에서 Injection 되는 객체 설정
//		
//	@BeforeEach
//	public void setUp() {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();		// test를 위한 MockMvc 객체 생성. testController 1개만 주입.		
//		mapper = new ObjectMapper();
//	}
//	
//	@DisplayName("# LoginController 컨트롤러 list 메서드 테스트")
//	@Test
//	public void testList() throws Exception {
//
//		UserBean userInfo = new UserBean(
//										 "unitTest@test.co.kr", 
//										 "unitTestName", 
//										 "123456789", 
//										 "0001112222", 
//										 "123456789",
//										 "KR", 
//										 new Timestamp(System.currentTimeMillis()), 
//										 new Timestamp(System.currentTimeMillis())
//										 );
//
//		this.mockMvc.perform(post("/api/users/list") // controller의 testURI를 POST로 호출
//					.contentType(MediaType.APPLICATION_JSON_VALUE) // contentType은 json 형식
//					.content(mapper.writeValueAsString(userInfo)))
//					.andExpect(status().isOk())
//					.andDo(print());
//
//	}
//
//}
