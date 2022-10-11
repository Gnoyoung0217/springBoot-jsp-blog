package com.jerryLog.www;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// WebSecurityConfigureAdapder 지원 중단으로 인한 GlobalMethodSecurityConfiguration
// 참조경로 1 >> https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
// 참조경로 2 >> https://velog.io/@chiyongs/Spring-deprecated된-WebSecurityConfigurerAdapter-이젠-안녕

@Configuration
public class SpringSecurityConfig {
	
	// HttpSecurity
	// 권한 method 설정 https://kimchanjung.github.io/programming/2020/07/02/spring-security-02/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				   .cors().disable()
				   .httpBasic().disable()
				   .formLogin().disable()
				   .authorizeHttpRequests().antMatchers("/api/user/**").hasAuthority("ADMIN")
				   .antMatchers("/api/user/**").permitAll()
				   .and()
				   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				   .and()
				   .authorizeRequests().anyRequest().permitAll()
				   .and().build();
//				   
//				   .exceptionHandling()
//				   .authenticationEntryPoint(((request, response, authException) -> {
//					   response.setStatus(HttpStatus.UNAUTHORIZED.value());
//					   response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//					   new ObjectMapper.writeValue(
//							   response.getOutputStream(),
//							   ExceptionResponse.of(ExceptionCode.FAIL_AUTHENTICATION)
//							   );
//				   })
//						   );
			
	}

	// WebSecurity 스프링 front 설정
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().mvcMatchers("/css/**", "/js/**", "/img/**");
	}
	
	// WebSecurity를 사용하여 특정 request를 ignore 하려고 하면,
	// HttpSecurity의 authorizeHttpRequests의 permitAll 메서드를 사용하는것을 권장

}
