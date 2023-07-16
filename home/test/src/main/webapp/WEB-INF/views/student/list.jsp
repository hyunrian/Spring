<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
$(function() {
	$(".btnUpdate").click(function(e) {
		e.preventDefault();
		var sno = $(this).attr("href");
		location.href="/student/read?sno=" + sno;
	});
	
	$(".btnDelete").click(function(e) {
		e.preventDefault();
		var sno = $(this).attr("href");
		console.log("sno: ", sno);
		location.href="/student/delete?sno=" + sno;
	});
});
</script>
</head>
<body>
	<h1>Student List</h1>
	<div><a href="insert">학생 정보 입력</a></div><br>
	<table border="1px" text-align="center">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>성별</th>
			<th>전공</th>
			<th>점수</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="studentVo" items="${list}">
			<tr>
				<td>${studentVo.sno}</td>
				<td>${studentVo.sname}</td>
				<td>${studentVo.sgender}</td>
				<td>${studentVo.smajor}</td>
				<td>${studentVo.sscore}</td>
				<td><a href="${studentVo.sno}" class="btnUpdate">수정</a></td>
				<td><a href="${studentVo.sno}" class="btnDelete">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>