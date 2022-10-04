<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<title>signup.jsp</title>

</head>

<body>
	<header> </header>
	<main>
		<form id="signUp_form" class="form_class" onSubmit="return false;">
			<h1>Sign Up</h1>
			<br /> <br />
			<div class="form_div">
				<label>Email</label>
				<input class="field_class" name="signupEmail" type="email" placeholder="Insert Email Address" autofocus />
				<label>Name</label>
				<input class="field_class" name="signupName" type="text" placeholder="Insert Name" />
				<label>PhoneNum</label>
				<input class="field_class" name="signupPhoneNum" type="text" placeholder="Insert PhoneNumber" />
				<label>Password</label>
				<input class="field_class" name="signupPw" type="password" placeholder="Insert a Password">
				<label>Confirm Password</label>
				<input class="field_class" name="signupConfirmPw" type="password" placeholder="Insert a Password">
				
				<p id="pwChk"></p>
				<br />
				<button class="submit_class" type="submit" form="login_form" onclick="fn_signUp();">Sign Up</button>
			</div>
		</form>
	</main>
	<footer> </footer>
	<script>
		// document.querySelector(".form_div #asd").value = '${name}';
		
		fn_init = () => {
			document.querySelector('#pwChk').style.visibility = 'hidden';
		}
		fn_init();
		
		
		fn_signUp = () => {
			const param = {
				'email' : document.querySelector('#signUp_form [name="signupEmail"]').value,
				'name' : document.querySelector('#signUp_form [name="signupName"]').value,
				'phoneNum' : document.querySelector('#signUp_form [name="signupPhoneNum"]').value,
				'password' : document.querySelector('#signUp_form [name="signupPw"]').value,
				'confirmPassword' : document.querySelector('#signUp_form [name="signupConfirmPw"]').value
			};
			
			if(param.password !== param.confirmPassword) {
				document.querySelector('#pwChk').innerText = '비밀번호를 다시 확인해주세요';
				document.querySelector('#pwChk').style.visibility = 'visible';
				document.querySelector('#signUp_form [name="signupPw"]').focus();
				return;
			} else {
				document.querySelector('#pwChk').style.visibility = 'hidden';
				fn_asyncRequest(param);
			}
			
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
			xhr.open("POST", "/api/users/signup"); // 서버로 보낼 Ajax 요청의 형식 설정
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.send(JSON.stringify(param)); // Ajax 요청을 서버로 전달	
		}
		
	</script>
</body>
</html>