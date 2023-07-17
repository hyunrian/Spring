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
</head>
<body>
	<h1>글쓰기</h1>
	<div class="col-md-12">
		<form action="/board/insert" method="post">
			<input type="text" name="title" placeholder="제목" width="100%"><br>
			<textarea rows="10" name="content" placeholder="내용"></textarea><br>
			<input type="text" name="writer" placeholder="작성자" width="100%"><br>
			<button type="submit">등록하기</button>
		</form>
	</div>
</body>
</html>