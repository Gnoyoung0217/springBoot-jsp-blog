<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<title>SignUp</title>

</head>

<body>
	<header> </header>
	<main>
		<form id="signUp_form" class="form_class" onSubmit="return false;">
			<h1>Sign Up</h1>
			<br /> <br />
			<div class="form_div">
				<label>Email(계정)</label>
				<input class="field_class" name="email" type="email" placeholder="Insert Email Address" autofocus />
				<label>Name(이름)</label>
				<input class="field_class" name="name" type="text" placeholder="Insert Name" />
				<label>PhoneNum(전화번호)</label>
				<input class="field_class" name="phoneNum" type="text" placeholder="Insert PhoneNumber" />
				<label>Region(국가)</label>
				<select class="field_class" name="region">
					<option>KR</option>
					<option>EN</option>
					<option>US</option>
					<option>UK</option>
					<option>JP</option>
					<option>CN</option>
				</select>

				<label>Password(비밀번호)</label>
				<input class="field_class" name="password" type="password" placeholder="Insert a Password">
				<label>Confirm Password(비밀번호 확인)</label>
				<input class="field_class" name="confirmPassword" type="password" placeholder="Insert a Password">

				<p id="pwChk" style="visibility: hidden"></p>
				<br />
				<input class="submit_class" type="button" name="cancleBtn" onclick="history.back()" style="display: inline-block;"value="BACK">
				<button class="submit_class" type="submit" name="signupBtn" onclick="fn_signUp();" style="display: inline-block; background-color: grey" disabled>Sign Up</button>
			</div>
		</form>
	</main>
	<footer> </footer>
	<script>
		// document.querySelector(".form_div #asd").value = '${name}';
		const email = document.querySelector('#signUp_form [name="email"]');
		const name = document.querySelector('#signUp_form [name="name"]');
		const phoneNum = document.querySelector('#signUp_form [name="phoneNum"]');
		const region = document.querySelector('#signUp_form [name="region"]');
		const password = document.querySelector('#signUp_form [name="password"]');
		const confirmPassword = document.querySelector('#signUp_form [name="confirmPassword"]');
		const signupBtn = document.querySelector('#signUp_form [name="signupBtn"]');
		 
		let eventHandlerInputChk = () => {
			if (email.value.length > 0 
				&& name.value.length > 0 
				&& phoneNum.value.length > 0
				&& region.value.length > 0
				&& password.value.length > 0
				&& confirmPassword.value.length > 0) {
				
				signupBtn.disabled = false
				//signupBtn.removeAttribute('style');
			  	signupBtn.style.backgroundColor = '#FFE6D4';
			} else {
				signupBtn.disabled = true
			  	signupBtn.style.backgroundColor = 'grey';
			}
		}
		
		email.addEventListener('keyup', eventHandlerInputChk);
		name.addEventListener('keyup', eventHandlerInputChk);
		phoneNum.addEventListener('keyup', eventHandlerInputChk);
		region.addEventListener('keyup', eventHandlerInputChk);
		password.addEventListener('keyup', eventHandlerInputChk);
		confirmPassword.addEventListener('keyup', eventHandlerInputChk);
		
		fn_signUp = () => {
 			const param = {
				'email' : email.value,
				'name' : name.value,
				'phoneNum' : phoneNum.value,
				'password' : password.value,
				'region' : region.value,
				'confirmPassword' : confirmPassword.value
			};
						
			if(param.password !== param.confirmPassword) {
				document.querySelector('#pwChk').innerText = '비밀번호가 일치하지 않습니다.';
				document.querySelector('#pwChk').style.visibility = 'visible';
				password.focus();
				return;
			} else {
				document.querySelector('#pwChk').style.visibility = 'hidden';
				fn_asyncRequest(param);
			}
		}
		
		// 비동기function
		fn_asyncRequest = (param) => {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = () => {
				if (xhr.readyState == 4 && xhr.status == 200) {
					let data = JSON.parse(xhr.responseText);
					
					if (data.result == 'Success') {
						alert('회원가입을 환영합니다.');
						window.location.href = '/api/page/login';
					} else if (data.result == 'Fail') {
						alert('회원가입에 실패하였습니다.\n' + data.message);
					} else {
						alert('관리자에게 문의해주세요');
					}
					
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