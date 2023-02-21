<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타임라인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		<section class="d-flex justify-content-center">
			<div class="postbox">
				<div class="postwirte-box border rounded">
					<%-- 입력 박스 --%>
					<div>
						<textarea rows="8" class="form-control border-0" id="contentInput"></textarea>
					</div>
					<%-- /입력 박스 --%>
					
					<%-- 파일 업로드 버튼 --%>
					<div class="d-flex justify-content-between my-2 mx-2 align-items-center">
						<div class="image-upload">
	   						 	<label for="fileInput">
	        						<div class="upload-icon text-warning" id="imageUploadBtn"><i class="bi bi-image"></i></div>
	    						</label>
	    					<input id="fileInput" type="file" style="display: none;"/>
						</div> 
						<div>
							<button type="button" class="btn btn-info" id="uploadBtn">업로드</button>
						</div>
					</div>
					<%-- /파일 업로드 버튼 --%>
				</div>
				
				<%-- 카드 --%>
				
				<c:forEach var="post" items="${postList }" >
				
				<div class="card mt-3">
					<div class="d-flex justify-content-between p-2">
						<div>${post.userName }</div>
						<i class="bi bi-three-dots"></i>
					</div>
					
					<div class="image-box mt-2">
						<img src="${post.imagePath}"  width="100%">
					</div>
					
					<div class="like-box d-flex  p-2 ">
						<i class="bi bi-suit-heart mr-2 heartBtn" data-post-id="${post.id }"></i> 좋아요 11개
					</div>
			
					<div class=" p-2">
						<b class="mr-2">${post.userName }</b> ${post.content }
					</div>
					
					
					
					
					<%-- 댓글 들 --%>
					<div class="comment-box p-2">
						<div>댓글</div>
							<hr>
							<div><b>유재석</b> 우왕</div>							
							<div><b>조세호</b> 우왕2</div>							
							
							<div class="d-flex">
								<input type="text" class="form-control" id="comment-content" value="${post.id }">
								<button type="button" class="btn btn-info" id="post">게시</button>
							</div>
					</div>
					<%-- /댓글 들 --%>
				</div>
				<%-- /카드 --%>
				
				</c:forEach>
				
<%-- 				카드
				<div class="card mt-3">
					<div class="d-flex justify-content-between p-2">
						<div>박기석12</div>
						<i class="bi bi-three-dots"></i>
					</div>
					
					<div class="image-box mt-2">
						<img src="https://cdn.pixabay.com/photo/2023/02/02/13/27/cat-7762887_960_720.jpg"  width="100%">
					</div>
					
					<div class="like-box d-flex  p-2 ">
						<i class="bi bi-suit-heart mr-2"></i>
						<i class="bi bi-chat mr-2"></i> <b>좋아요 11개</b>
					</div>
			
					<div class=" p-2">
						<b class="mr-2">박기석12</b> 오늘 지하철 타고 강남을 다녀왔다.배경이
					</div>
					
					
					
					
					댓글 들
					<div class="comment-box p-2">
						<div>댓글</div>
							<hr>
							<div><b>유재석</b> 우왕</div>							
							<div><b>조세호</b> 우왕2</div>							
							
							<div class="d-flex">
								<input type="text" class="form-control ">
								<button type="button" class="btn btn-info ">게시</button>
							</div>
					</div>
					/댓글 들
				</div>
				/카드 --%>
			</div>
		</section>
		
		
		
		
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	
	</div>
	
	<script>
		$(document).ready(function(){

			
 			$("#post").on("click", function(){
				
				let val = $("#post").val();
				let content = $("#comment-content").val();
			
				

 			});
			
			
				
				
				
				
				
			$(".heartBtn").on("click",function(){
				
				//해당하는 버튼에 대응되는 post id를 얻어와야 한다.
				let id = $(this).data("post-id");
				
				$.ajax({
					type:"get"
					, url : "/post/like"
					, data:{"postId":id}
					, success:function(data){
						if(data.result == "success"){
							location.reload();
						} else{
							alert("좋아요 실패");
						}
						
					}
					,error:function(){
						alert("좋아요 에러");
					}
				});
				
				
			});
			
			
			$("#uploadBtn").on("click",function(){
				let content = $("#contentInput").val();
				let file = $("#fileInput").val();
				
				
				if(content == ""){
					alert("내용을 입력하세요");
					return;
				}
				
				// 파일이 선택되 않았을때
				if($("#fileInput")[0].files.length == 0){
					alert("파일을 첨부해주세요");
					return;
				}
				
				var formData = new FormData();
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0])
				
				$.ajax({
					type:"post"
					, url:"/post/write"
					, data:formData
					, enctype:"multipart/form-data"
					, processData:false
					, contentType:false
					, success:function(data){
						if(data.result == "success"){
							/* location.reload(); */
							location.href="/post/timeline/view";
						} else{
							alert("업로드 실패")
						}
					}
					, error:function(){
						alert("업로드 에러")
					}
				
				});
				
				
			});
			
			
			
		});
	</script>
	
</body>
</html>