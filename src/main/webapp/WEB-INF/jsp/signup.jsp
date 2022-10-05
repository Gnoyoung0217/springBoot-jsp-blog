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
	<!-- <header> </header> -->
	<main>
		<form id="signUp_form" class="form_class" onSubmit="return false;">
			<h1>Sign Up</h1>
			<br /> <br />
			<div class="form_div">
				<label>Email</label>
				<input class="field_class" name="email" type="email" placeholder="Insert Email Address" autofocus />
				<label>Name</label>
				<input class="field_class" name="name" type="text" placeholder="Insert Name" />
				<label>PhoneNum</label>
				<input class="field_class" name="phoneNum" type="text" placeholder="Insert PhoneNumber" />
				<label>Region</label>
				<select class="field_class" name="region">
					<option>KR</option>
					<option>EN</option>
					<option>US</option>
					<option>JP</option>
					<option>CN</option>
				</select>

				<label>Password</label>
				<input class="field_class" name="password" type="password" placeholder="Insert a Password">
				<label>Confirm Password</label>
				<input class="field_class" name="confirmPassword" type="password" placeholder="Insert a Password">

				<p id="pwChk"></p>
				<br />
				<button class="submit_class" type="submit" form="login_form" onclick="fn_signUp();">Sign Up</button>
			</div>
		</form>
	</main>
	<!-- <footer> </footer> -->
	<script>
		// document.querySelector(".form_div #asd").value = '${name}';
		
		fn_init = () => {
			document.querySelector('#pwChk').style.visibility = 'hidden';
		}
		fn_init();
		
		fn_signUp = () => {
 			const param = {
				'email' : document.querySelector('#signUp_form [name="email"]').value,
				'name' : document.querySelector('#signUp_form [name="name"]').value,
				'phoneNum' : document.querySelector('#signUp_form [name="phoneNum"]').value,
				'password' : document.querySelector('#signUp_form [name="password"]').value,
				'region' : document.querySelector('#signUp_form [name="region"]').value,
				'confirmPassword' : document.querySelector('#signUp_form [name="confirmPassword"]').value
			};
						
			if(param.password !== param.confirmPassword) {
				document.querySelector('#pwChk').innerText = '비밀번호가 일치하지 않습니다.';
				document.querySelector('#pwChk').style.visibility = 'visible';
				document.querySelector('#signUp_form [name="password"]').focus();
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