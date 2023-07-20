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
	
	// 수정, 삭제 후 목록으로 가면 보던 페이지 유지 - 수업 때 한 방법
	// form 태그로 파라미터를(전달할 데이터 전체) 다음 페이지로 전달
	// 내가 했던 방식과 차이를 보려면 드라이브에 업로드된 파일 참고
// 	$(".a_title").click(function(e) { 
// 		e.preventDefault();
// 		alert("ddd");
// 		var bno = $(this).attr("href");
// 		$("input[name=bno]").val(bno);
// 		$("#frmPaging").attr("action", "/board/read");
// 		$("#frmPaging").submit();
// 	});
	
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
	<%-- 여기에 쓰다 만 코드가 있어서 script 내 함수가 제대로 작동하지 않는 에러가 있었음 --%>
	
	// 댓글 개수 뱃지
	$(".a_title").next().click(function() {
		var bno = $(this).parent().prev().text();
		console.log(bno);
		location.href="/board/read?bno=" + bno + "#replyListDiv";
		// 기능이 완전히 구현된게 아님. 이런 기능이 있다는 것만 일단 알아두자
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

<%@ include file="/WEB-INF/views/include/frmPaging.jsp" %>

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
									<td><a href="/board/read?bno=${boardVo.bno}" class="a_title">${boardVo.title}</a>
									<c:if test="${boardVo.replycnt != 0}">
										<span class="badge badge-info"
											style="cursor:pointer; margin-left:10px;">${boardVo.replycnt}</span>
									</c:if>
<%-- 									<td><a class="a_title" href="${boardVo.bno}">${boardVo.title}</a></td> --%>
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
						<a class="page-link " href="${v}">${v}</a>
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