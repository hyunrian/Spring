<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<style>
#uploadDiv {
	width: 80%;
	height: 100px;
	background-color: lightpink;
	margin: 20px auto;
	border: 2px dashed purple;
}
</style>
<script>
$(function() {
	$("#uploadDiv").on("dragenter dragover", function(e) { // 영역 안으로 들어올 때 함수 실행
		e.preventDefault();
	});
	$("#uploadDiv").on("drop", function(e) { // 영역 안에 드롭할 때 함수 실행
		// 비동기로 파일 전송하기
		e.preventDefault();
		console.log(e);
		let file = e.originalEvent.dataTransfer.files[0]; // 파일 정보 얻기
		console.log(file);
		// <form enctype="multipart/form-data" action="/upload/uploadFile">
		// 	<input type="file" name="file">
		// </form> 의 역할을 하는 코드가 필요함
		let formData = new FormData(); // html5부터 사용
		formData.append("file", file); // file이라는 이름으로 file 전송
		let url = "/upload/uploadFile";
		$.ajax({
			"type" : "post",
			"processData" : false, // 파라미터 형식으로 보내지 않음
			"contentType" : false, // text데이터가 아닌 binary data임을 알림
			"url" : url,
			"data" : formData,
			"success" : function(rData) {
				console.log(rData);
			}
		});
	});
});
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글쓰기 양식</h2><br>
				<p><a href="/board/list" class="btn btn-success">글 목록으로 이동</a></p>
				<%-- a링크로 이동 : get방식 --%>
			</div>
			<form role="form" action="/board/register" method="post"
				enctype="multipart/form-data"> <%-- 파일처리(binary data)를 위한 데이터 타입 --%>
				<input type="file" name="file">
				<div class="form-group">
					<label for="title">제목</label> 
					<%-- lable for: id랑 같게 맞춤 --%>
					<input type="text" class="form-control" 
						id="title" name="title"/>
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" 
						id="content" name="content"></textarea>
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input type="text" class="form-control" 
						id="writer" name="writer"/>
				</div>
				
				<div id="uploadDiv">
					
				</div>
				
				<button type="submit" class="btn btn-primary">
					작성완료
				</button>
			</form>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>