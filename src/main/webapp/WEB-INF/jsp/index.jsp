<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head> 
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<title>index.jsp</title>
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
				<input class="field_class" name="loginEmail" type="email" placeholder="Insert Email Address" autofocus>
				<label>Password</label>
				<input id="pass" class="field_class" name="loginPw" type="password" placeholder="Insert a Password">
				<button class="submit_class" type="submit" form="login_form" onclick="fn_login()">Login</button>
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
		fn_login = () => {
			const param = {
				'email' : document.querySelector('#login_form [name="loginEmail"]').value,
				'password' : document.querySelector('#login_form [name="loginPw"]').value
			};
			
			fn_asyncRequest(param);
		}
		
		fn_asyncRequest = (param) => {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = () => {
				if (xhr.readyState == 4 && xhr.status == 200) {
					alert(xhr.responseText);
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
