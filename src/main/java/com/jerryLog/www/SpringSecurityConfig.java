package com.jerryLog.www;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jerryLog.www.service.CustomUserDetailsService;




// WebSecurityConfigureAdapder 지원 중단으로 인한 GlobalMethodSecurityConfiguration
// 참조경로 1 >> https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
// 참조경로 2 >> https://velog.io/@chiyongs/Spring-deprecated된-WebSecurityConfigurerAdapter-이젠-안녕

@Configuration
public class SpringSecurityConfig {
	
	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		System.out.println("너 언제하냐");
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	}
	
	@Bean
	public UserDetailsService userDetailService() {
		System.out.println("이제 실행돼?");
		return new CustomUserDetailsService();
	}
	
	// 참조경로 https://velog.io/@jummi10/Spring-Security-0k15d6af
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

		// antMatchers("/login**", "/web-resources/**") : 특정 리소스에 대해서 권한을 설정
		// permitAll() : 설정한 리소스의 접근을 인증없이 허용
		// hasAnyRole("ADMIN") : 인증 후 ADMIN 레벨의 권한을 가진 사용자만 접근 허용
		// anyRequest().authenticated() : 모든 리소스를 의미하며 접근허용 리소스 및 인증 후 
		// 특정 레벨의 권한을 가진 사용자만 접근가능한 리소스를 설정하고 그 외 나머지 리소스들을 무조건 인증을 완료해야 접근 가능
		
		// formLogin() : 로그인 처리 성공 실패 기능을 사용하는 것.
		// loginPage("/login-page") : 사용자가 따로 만든 로그인 페이지를 사용하려할때 설정
		// loginProcessingUrl("/login-process") : 로그인 인증 처리를 하는 URL 설정
		// defaultSuccessUrl("/main") : 정상적으로 인증 성공 했을 경우 이동하는 페이지 설정
		// failuereUrl("/login-fail") : 인증이 실패했을 경우 이동하는 페이지 설정
			http
					.csrf().disable()					// Cross Site Script Forgery
			   		.cors().disable()					// Cross Origin
//			   		.httpBasic().disable()
			   		.authenticationProvider(authenticationProvider())
					.authorizeRequests()
						//.antMatchers("/**").permitAll() // 설정한 리소스의 접근을 인증없이 허용
						.antMatchers("/api/page/login").anonymous() // 설정한 리소스의 접근을 인증없이 허용
						.antMatchers("/api/users/login").anonymous() // 설정한 리소스의 접근을 인증없이 허용
						//.antMatchers("/api/users/login").permitAll() // 설정한 리소스의 접근을 인증없이 허용
						.antMatchers("/api/page/home").hasAnyAuthority("USER")
						.anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
						.and()
					.formLogin()
						.loginPage("/api/page/login")
						.defaultSuccessUrl("/api/page/home")
						.permitAll()
						// .usernameParameter("loginEmail")
						// .passwordParameter("loginPw")
				   		.and()
				   	.logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
						.deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
						.invalidateHttpSession(true) // 로그아웃 시 Session 종료
						.clearAuthentication(true);	 // 로그아웃 시 권한 제거
				   //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			return http.build();
	}

	// WebSecurity 스프링 앞단 보안설정
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().mvcMatchers("/resources/css/**", "/resources/js/**");
	}
	
	// WebSecurity를 사용하여 특정 request를 ignore 하려고 하면,
	// HttpSecurity의 authorizeHttpRequests의 permitAll 메서드를 사용하는것을 권장

}
