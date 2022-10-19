package com.jerryLog.www.security;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 필수 참조 : https://catsbi.oopy.io/c0a4f395-24b2-44e5-8eeb-275d19e2a536
@Configuration
public class SecurityConfig {
	
	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authProvider;
	}
	
	// HttpSecurity 보안설정
	// 권한 method 설정 https://kimchanjung.github.io/programming/2020/07/02/spring-security-02/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

			http
					.csrf().disable()						// Cross Site Script Forgery
						.authorizeRequests()				// 요청에 의한 보안검사 시작
						.antMatchers("/api/page/login").permitAll()
						.antMatchers("/api/page/signup").permitAll()
						.antMatchers("/api/users/signup").permitAll()
						.antMatchers("/api/page/home").hasAnyAuthority("USER")
						.anyRequest().authenticated() 		// 위 외 어떠한 요청에도 보안검사를 한다.
					.and()
						.formLogin()
						.loginPage("/api/page/login")
						.loginProcessingUrl("/api/users/login")
						.defaultSuccessUrl("/api/page/home")
						.usernameParameter("email")
						.passwordParameter("password")
						// 로그인 성공 후 핸들러
						//.successHandler(new AuthenticationSuccessHandlerImpl())
						//로그인 실패 핸들러
						.failureHandler(new AuthenticationFailureHandlerImpl())//로그인 실패 후 핸들러
						.permitAll()
				   	.and()
				   		.logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/api/users/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
						.logoutSuccessUrl("/")
						.deleteCookies("JSESSIONID", "remember-me") // 로그아웃 시 JSESSIONID 제거
						.addLogoutHandler(new LogoutHandler() {
							@Override
							public void logout(HttpServletRequest request,
									           HttpServletResponse response,
									           Authentication authentication) {
								HttpSession session = request.getSession();
								session.invalidate();
							}
						})
						.logoutSuccessHandler(new LogoutSuccessHandler() {
							@Override
							public void onLogoutSuccess(HttpServletRequest request,
														HttpServletResponse response,
														Authentication authentication) throws IOException, ServletException {
								
								response.sendRedirect("/");
							}
						})
						.deleteCookies("remember-me"); // 쿠키 삭제
			return http.build();
	}

	// WebSecurity 스프링 앞단 보안설정
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().mvcMatchers("/resources/css/**", "/resources/js/**");
	}

	public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,
											HttpServletResponse response,
											Authentication authentication) throws IOException, ServletException {
			
			System.out.println("authentication:: " + authentication.getName());
			response.sendRedirect("/");
		}
		
	}
	
	public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, 
											HttpServletResponse response,
											AuthenticationException exception) throws IOException, ServletException {
			System.out.println("exception :: " + exception.getMessage());
			
			String msg = "100001";
			if (exception instanceof DisabledException) {	// 계정 비활성화
				msg = "100002";
			} else if (exception instanceof BadCredentialsException)	{ // 비밀번호 불일치
				msg = "100003";
			}
			
			response.sendRedirect("/api/page/login?msg=" + msg);
		}
	}
	
}
