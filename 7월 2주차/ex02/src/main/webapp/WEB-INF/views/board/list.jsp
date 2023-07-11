<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<script>
$(function() {
	var msg = "${msg}";
	if (msg == "SUCCESS") alert("처리가 완료되었습니다.");
});
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글 목록</h2><br>
				<p><a href="/board/register" class="btn btn-success">글쓰기</a></p>
				<%-- a링크로 이동 : get방식 --%>
			</div>
			<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="boardVo" items="${list}">
						<tr>
							<td>${boardVo.bno}</td>
							<td><a href="/board/read?bno=${boardVo.bno}">${boardVo.title}</a></td>
							<td>${boardVo.writer}</td>
							<td>${boardVo.regdate}</td>
							<td>${boardVo.viewcnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>