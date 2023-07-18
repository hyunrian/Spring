<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<script>
$(function() {
	$("#btnModify").click(function() {
		<!-- prop: 값이 true/false. 단독으로 사용되는 경우 ex) checked / 그 외: attr  -->
		$(".readonly").prop("readonly", false);
		$("#btnModifyFinish").fadeIn(1000);
		$(this).fadeOut(1000);
	});
	
	$(".goToList").click(function(e) {
		e.preventDefault();
		if (${pagingDto != null}) {
			$("input[name=page]").val("${pagingDto.page}");
			$("input[name=perPage]").val("${pagingDto.perPage}");
			$("input[name=searchType]").val("${pagingDto.searchType}");
			$("input[name=keyword]").val("${pagingDto.keyword}");
			var form = $("#frmPaging");
			form.submit();
		} else {
			location.href="/board/list";
		}
	});
});
</script>
<%@ include file="/WEB-INF/views/include/frmPaging.jsp" %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글 상세보기</h2><br>
<!-- 				<p><a href="/board/list" class="btn btn-success">글 목록으로 이동</a></p> -->
				<p><a class="btn btn-success goToList">글 목록으로 이동</a></p>
				<%-- a링크로 이동 : get방식 --%>
			</div>
			<form role="form" action="/board/mod" method="post">
				<input type="hidden" name="bno" value="${boardVo.bno}">
				<div class="form-group">
					<label for="title">제목</label> 
					<%-- lable for: id랑 같게 맞춤 --%>
					<input type="text" class="form-control readonly"
						id="title" name="title" value="${boardVo.title}" readonly/>
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control readonly" 
						id="content" name="content" readonly>${boardVo.content}</textarea>
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input type="text" class="form-control readonly" 
						id="writer" name="writer" value="${boardVo.writer}" readonly/>
				</div>
				<button type="button" class="btn btn-warning" id="btnModify">
					수정
				</button>
				<button type="submit" class="btn btn-primary" 
					id="btnModifyFinish" style="display:none;">
					수정완료
				</button>
				<a href="/board/delete?bno=${boardVo.bno}" class="btn btn-danger">
					삭제
				</a>
			</form>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>