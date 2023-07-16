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
	<h1>학생 정보 수정</h1>
	<form action="/student/update" method="post">
		학번<br>
		<input type="number" name="sno" value="${studentVo.sno}" readonly><br>
		이름<br>
		<input type="text" name="sname" value="${studentVo.sname}"><br>
		성별<br>
		<c:choose>
			<c:when test="${studentVo.sgender == 'M'}">
				<input type="radio" name="sgender" value="M" checked> 남자
				<input type="radio" name="sgender" value="F"> 여자
			</c:when>
			<c:otherwise>
				<input type="radio" name="sgender" value="M"> 남자
				<input type="radio" name="sgender" value="F" checked> 여자
			</c:otherwise>
		</c:choose>
		<br>
		전공<br>
		<select name="smajor">
			<c:choose>
				<c:when test="${studentVo.smajor == '컴퓨터공학'}">
					<option value="컴퓨터공학" selected>컴퓨터공학</option>
					<option value="화학">화학</option>
					<option value="피아노">피아노</option>
				</c:when>
				<c:when test="${studentVo.smajor == '화학'}">
					<option value="컴퓨터공학" >컴퓨터공학</option>
					<option value="화학" selected>화학</option>
					<option value="피아노">피아노</option>
				</c:when>
				<c:otherwise>
					<option value="컴퓨터공학" >컴퓨터공학</option>
					<option value="화학">화학</option>
					<option value="피아노" selected>피아노</option>
				</c:otherwise>
			</c:choose>
		</select><br>
		점수<br>
		<input type="number" name="sscore" value="${studentVo.sscore}"><br>
		<button type="submit">수정완료</button>
	</form>
</body>
</html>