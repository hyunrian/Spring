<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생 정보 등록</h1>
	<form action="/student/insert" method="post">
		이름<br>
		<input type="text" name="sname"><br>
		성별<br>
		<input type="radio" name="sgender" value="M" checked> 남자
		<input type="radio" name="sgender" value="F"> 여자
		<br>
		전공<br>
		<select name="smajor">
			<option value="컴퓨터공학">컴퓨터공학</option>
			<option value="화학">화학</option>
			<option value="피아노">피아노</option>
		</select><br>
		점수<br>
		<input type="number" name="sscore"><br>
		<button type="submit">입력완료</button>
	</form>
</body>
</html>