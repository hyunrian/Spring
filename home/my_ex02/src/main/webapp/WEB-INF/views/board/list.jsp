<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>
$(function() {
	$(".detail").click(function() {
		var bno = $(this).attr("data-bno");
// 		console.log("bno:", bno);
		location.href="/board/detail?bno=" + bno;
	});
	
	$("#perPage").change(function() {
		var perPage = $(this).val();
// 		console.log("perPage:" + perPage);
		location.href="/board/list?perPage=" + perPage;
	});
	
	// 이 기능부터 다시 하면 됨. 페이지번호를 눌러도 이동이 안되고 있음
	$(".page-link").click(function(e) {
		e.preventDefault();
		var nowPage = $(this).attr("href");
		console.log("nowPage:" + nowPage);
		$(input[name='nowPage']).val(nowPage);
		$("#frmPaging").submit();
	});
});
</script>
<body>
	<form action="/board/list" method="get" id="frmPaging">
		<input type="hidden" name="nowPage" value="${dto.nowPage}">
		<input type="hidden" name="perPage" value="${dto.perPage}">
	</form>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h2>자유게시판</h2>
					<p>
						<a class="btn btn-primary btn-large" href="/board/insert">글 작성하기</a>
					</p>
				</div>
			</div>
		</div>
		<div>
			<select name="perPage" id="perPage">
				<c:forEach var="v" begin="5" end="20" step="5">
					<option value="${v}"
						<c:if test="${v == dto.perPage}">
							selected
						</c:if>
					>${v}개씩 보기</option>
				</c:forEach>
			</select>
		</div><br>
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="boardVo" items="${list}">
							<tr>
								<td>${boardVo.bno}</td>
								<td><a class="detail" data-bno="${boardVo.bno}">${boardVo.title}</a></td>
								<td>${boardVo.writer}</td>
								<td>${boardVo.regdate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<nav>
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href=
								"/board/list?nowPage=${dto.startPage - 1}">&lt;&lt;</a>
						</li>
						<c:forEach var="v" begin="${dto.startPage}" end="${dto.endPage}">
							<c:choose>
								<c:when test="${v == dto.nowPage}">
									<li class="page-item active">
								</c:when>
								<c:otherwise>
									<li class="page-item">
								</c:otherwise>
							</c:choose>
								<a class="page-link" href="${v}">${v}</a>
							</li>
						</c:forEach>
						<li class="page-item">
							<a class="page-link" href="/board/list?nowPage=${dto.endPage + 1}">&gt;&gt;</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>