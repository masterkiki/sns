<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap" class="d-flex">
		<section class="d-flex">
			<div class="image d-flex justify-content-center align-items-center">
				<img height="500" src="https://cdn.pixabay.com/photo/2013/07/13/12/46/iphone-160307_960_720.png">
			</div>
			<div class="signinsection">
				<div class="signin-insert d-flex justify-content-center align-items-center">
					<div class="signin-box">
						<h1 class="text-center">Community</h1> <br>
						<form id="loginForm">
							<input type="text" class="form-control" placeholder="아이디를 입력하세요" id="idInput"> <br>
							<input type="password" class="form-control " placeholder="비밀번호를 입력하세요" id="passwordInput"> <br>
							<button type="submit" class="btn btn-info btn-block" id="signinBtn">로그인</button>
						</form>
					</div>
				</div>
				<div class="signup mt-4 d-flex justify-content-center align-items-center">
					<div class="signup-box text-center">
						<label>계정이 없으신가요?</label><a href="#" class="text-info font-weight-bold">가입하기</a>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<script>
		$(document).ready(function(){
		
			
			$("#loginForm").on("submit", function(e){
			//$("#signinBtn").on("click", function(){
				
			// 해당 이벤트의 기능을 모두 취소한다
			e.preventDefault();
				
				let loginId = $("#idInput").val();
				let password = $("#passwordInput").val();
				
				if(loginId == ""){
					alert("아이디를 입력하세요");
					return;
				}
				if(password == ""){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post"
					, url:"/user/signin"
					, data:{"loginId":loginId, "password":password}
					, success:function(data){
						if(data.result == "success"){
							location.href = "/post/timeline/view";
						} else{
							alert("아이디 비밀번호를 확인해주세요");
						}
					}
					, error:function(){
						alert("로그인 에러");
					}
				});
				
				
			});
			
			
		});
	</script>

</body>
</html>