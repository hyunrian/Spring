<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<script>
$(function() {
	var msg = "${msg}";
	if (msg == "SUCCESS") alert("처리가 완료되었습니다.");
	
	$(".page-link").click(function(e) {
		e.preventDefault(); // a태그의 동작을 막음
		var page = $(this).attr("href");
		$("input[name=page]").val(page); // input 중 name이 page인 것의 값을 page로 변경
		// 검색결과가 총 2페이지가 나왔을 때 1페이지에서 2페이지로 이동 시 바뀐 page를 반영하기 위함 
		var form = $("#frmPaging");
		form.submit();
	});
	
	$("#perPage").change(function() { <%-- 값이 바뀔 때 --%>
		var perPage = $(this).val();
		console.log("perPage:", perPage);
		location.href="/board/list?perPage=" + perPage;
	});
	
	$("#btnSearch").click(function() {
		var searchType = $("#searchType").val();
		var keyword = $("#keyword").val();
		location.href="/board/list?searchType=" + searchType + "&keyword=" + keyword;
	});
});
</script>
<style>
	ul.pagination {
		margin-left: 600px;
		margin-right: auto;
	}
	li.page-item {
 		display:block;	 
	}
</style>

<!-- 각 페이지 링크에 파라미터 설정(어떤 조건이든 페이징이 잘 되도록 하기 위함) -->
<form id="frmPaging" action="/board/list" method="get">
	<input type="hidden" name="page" value="${pagingDto.page}">
	<input type="hidden" name="perPage" value="${pagingDto.perPage}">
	<input type="hidden" name="searchType" value="${pagingDto.searchType}">
	<input type="hidden" name="keyword" value="${pagingDto.keyword}">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글 목록</h2><br>
				<p><a href="/board/register" class="btn btn-success">글쓰기</a></p>
				<%-- a링크로 이동 : get방식 --%>
			</div>
			
			<div class="row" style="margin-bottom:30px;">
				<!-- n줄씩 보기 -->
				<div class="col-md-2">
					<select name="perPage" id="perPage">
						<c:forEach var="v" begin="5" end="30" step="5">
							<option value="${v}"
								<c:if test="${pagingDto.perPage eq v}">
									selected
								</c:if> 
							>${v}줄씩 보기</option>
						</c:forEach>
					</select>
				</div>
					
				<!-- 검색 -->
				<div class="col-md-10">
					<select name="searchType" id="searchType">
						<option value="t"
							<c:if test="${pagingDto.searchType == 't'}">
								selected
							</c:if>
						>제목</option>
						<option value="c"
							<c:if test="${pagingDto.searchType == 'c'}">
								selected
							</c:if>
						>내용</option>
						<option value="w"
							<c:if test="${pagingDto.searchType == 'w'}">
								selected
							</c:if>
						>작성자</option>
						<option value="tc"
							<c:if test="${pagingDto.searchType == 'tc'}">
								selected
							</c:if>
						>제목+내용</option>
						<option value="tcw"
							<c:if test="${pagingDto.searchType == 'tcw'}">
								selected
							</c:if>
						>제목+내용+작성자</option>
					</select>
					<input type="text" name="keyword" id="keyword" 
						value="${pagingDto.keyword}">
					<button type="button" id="btnSearch">검색</button>
				</div>
				
			</div>
			
			<!-- 게시글 목록 -->
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
	
	<!-- 페이징 -->
	<div class="row">
		<div class="col-md-12">
			<nav>
				<ul class="pagination justtify-content-center">
					<li class="page-item">
						<c:if test="${pagingDto.startPage != 1}">
							<a class="page-link" href="${pagingDto.startPage - 1}">&laquo;</a>
						</c:if>
					</li>
					<c:forEach var="v" begin="${pagingDto.startPage}" end="${pagingDto.endPage}">
						<c:choose>
							<c:when test="${pagingDto.page == v}">
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
						<c:if test="${pagingDto.endPage < pagingDto.totalPage}">
							<a class="page-link" href="${pagingDto.endPage + 1}">&raquo;</a>
						</c:if>
					</li>
				</ul>
			</nav>
		</div>
	</div><!-- /페이징 -->
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>