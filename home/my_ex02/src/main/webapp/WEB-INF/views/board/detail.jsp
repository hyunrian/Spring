<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
$(function() {
	$("#btnUpdate").click(function() {
		$(this).hide();
		$("#btnUpdateCompleted").show();
		$(".updateForm").removeAttr("readonly");
	});
	$("#btnUpdateCompleted").click(function() {
		$("#frmUpdate").submit();
	});
	$("#btnDelete").click(function() {
		var bno = "${boardVo.bno}";
		location.href="/board/delete?bno=" + bno;
	});
});
</script>
</head>
<body>
	<h1>글 상세보기</h1>
	<a href="/board/list">목록으로 이동</a>
	<div class="container-fluid">
	<div class="row">
	<div class="col-md-12">
		<form action="/board/update" method="post" id="frmUpdate">
			<input type="hidden" name="bno" value="${boardVo.bno}">
			<input type="text" name="title" class="updateForm" placeholder="제목" value="${boardVo.title}" readonly><br>
			<textarea rows="10" cols="30" name="content" class="updateForm" placeholder="내용" readonly>${boardVo.content}</textarea><br>
			<input type="text" name="writer" class="updateForm" placeholder="작성자" value="${boardVo.writer}" readonly><br>
			<button type="button" id="btnUpdate" 
				class="btn btn-primary">수정</button>
			<button type="button" id="btnUpdateCompleted" 
				class="btn btn-primary" style="display:none;">수정완료</button>
			<button type="button" id="btnDelete" 
				class="btn btn-primary">삭제</button>
		</form>
	</div>
	</div>
	</div>
</body>
</html>