<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<title>Login</title>
</head>

<body onpageshow="fn_init(event);">
	<header> </header>
	<main>
		<!-- <form id="login_form" class="form_class" action="/api/login/login-access.jsp" method="post"> -->
		<!-- <form id="login_form" class="form_class" onSubmit="return false;"> -->
		<form id="login_form" class="form_class" action="/api/users/login" method='POST'>
			<h1>LOGIN</h1>
			<br /> <br />
			<div class="form_div">
				<label>Email</label>
				<input class="field_class" name="email" type="email" placeholder="Insert Email Address" required autofocus>
				<label>Password</label>
				<input id="pass" class="field_class" name="password" type="password" placeholder="Insert a Password" required>
				<button class="submit_class" type="submit" name="loginBtn" form="login_form" style="backgroundColor:grey" disabled >Login</button>
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

	<script type="text/javascript">
		fn_init = (e) => {
			if(e.persisted) console.log('back으로 접근');
		}
		
		<c:if test = "${not empty param.msg}">
			switch('${param.msg}') {
			case '100001' : 
				alert("계정/비밀번호가 유효하지 않습니다.");
				break;
			case '100002' : 
				alert("계정이 비활성화 상태입니다.");
				break;
			case '100003' : 
				alert("계정/비밀번호 정보를 다시 확인해주세요.");
				break;
			
			default : 
				alert("관리자에게 문의하여 주시기 바랍니다.");
				break;
			}
		</c:if>
		
			
		const email = document.querySelector('#login_form [name="email"]');
		const password = document.querySelector('#login_form [name="password"]');
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
		
		// 로그인 유효성체크
		fn_loginValidChk = () => {
			const param = {
				'email' : email.value,
				'password' : password.value
			};
			
			fn_asyncRequest(param);
		}
		
		// Async
		fn_asyncRequest = (param) => {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = () => {
				if (xhr.readyState == 4 && xhr.status == 200) {
					let data = JSON.parse(xhr.responseText);
					
					if (data.result == 'Success') {
						alert(param.email + " 님 환영합니다.");
					}
					else if (data.result == 'Fail') alert('이메일 및 비밀번호를 확인해주세요.');
					else alert('관리자에게 문의해주세요');
				} else {
					console.error(xhr.responseText);
				}
			};
			xhr.open("POST", "/api/users/loginValidChk", false); // 서버로 보낼 Ajax 요청의 형식 설정
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.send(JSON.stringify(param)); // Ajax 요청을 서버로 전달	
		}
		
		
	</script>
</body>

</html>
