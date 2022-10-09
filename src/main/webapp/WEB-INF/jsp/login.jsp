<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<title>Login</title>
</head>

<body>
	<header> </header>
	<main>
		<!-- <form id="login_form" class="form_class" action="/api/login/login-access.jsp" method="post"> -->
		<form id="login_form" class="form_class" onSubmit="return false;">
			<h1>LOGIN</h1>
			<br /> <br />
			<div class="form_div">
				<label>Email</label>
				<input class="field_class" name="loginEmail" type="email" placeholder="Insert Email Address" required autofocus>
				<label>Password</label>
				<input id="pass" class="field_class" name="loginPw" type="password" placeholder="Insert a Password" required>
				<button class="submit_class" type="submit" name="loginBtn" form="login_form" onclick="fn_login()" style="backgroundColor:grey" disabled >Login</button>
			</div>
			<div class="info_div">
				<p>
					Don't have an account?
					<a href="/api/page/signup">Sign Up!</a>
				</p>
			</div>
		</form>
	</main>
	<footer>
		<!-- <p>Desenvolvido por <a href="#">JM_Code&trade;</a></p> -->
	</footer>

	<script>
		const email = document.querySelector('#login_form [name="loginEmail"]');
		const password = document.querySelector('#login_form [name="loginPw"]');
		const loginBtn = document.querySelector('#login_form [name="loginBtn"]');
		
		// 입력정보 확인
		let eventHandlerInputChk = () => {
			if (email.value.length > 0 && password.value.length > 0) { 
			  	loginBtn.disabled = false
			  	loginBtn.removeAttribute('style');
			} else {
			  	loginBtn.disabled = true
			  	loginBtn.style.backgroundColor = 'grey';
			}
		}
		
		email.addEventListener('keyup', eventHandlerInputChk);
		password.addEventListener('keyup', eventHandlerInputChk);
		
		// 로그인 처리
		fn_login = () => {
			const param = {
				'email' : email.value,
				'password' : password.value
			};
			
			fn_asyncRequest(param);
		}
		
		// 비동기function
		fn_asyncRequest = (param) => {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = () => {
				if (xhr.readyState == 4 && xhr.status == 200) {
					let data = JSON.parse(xhr.responseText);
					
					if (data.result == 'Success') {
						alert('[' + data.userResult.name + '] 님 환영합니다.');
						window.location.href = '/api/page/';
					}
					else if (data.result == 'Fail') alert('사용자가 없습니다.');
					else alert('관리자에게 문의해주세요');
				} else {
					console.error(xhr.responseText);
				}
			};
			xhr.open("POST", "/api/users/login"); // 서버로 보낼 Ajax 요청의 형식 설정
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.send(JSON.stringify(param)); // Ajax 요청을 서버로 전달	
		}
		
		
		
	</script>
</body>

</html>
