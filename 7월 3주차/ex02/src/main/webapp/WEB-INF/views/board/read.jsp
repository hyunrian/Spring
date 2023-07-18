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
	
	function getReplyList() {
		$.get("/reply/list/${boardVo.bno}", function(rData) {
			console.log(rData);
			/*
			for (var v = 0; v < rData.length; v++) {
				var tr = $("#replyList").find("tr").eq(0).clone();
				var tds = tr.find("td");
				tds.eq(0).text(rData[v].rno);
				tds.eq(1).text(rData[v].replytext);
				tds.eq(2).text(rData[v].replyer);
				tds.eq(3).text(rData[v].regdate);
				tds.eq(4).text(rData[v].updatedate);
				$("#replyList").append(tr);
			}
			*/
			// 위와 같은 코드. foreach와 비슷
			$.each(rData, function() {
				var tr = $("#replyList").find("tr").eq(0).clone();
				var tds = tr.find("td");
				tds.eq(0).text(this.rno);
				tds.eq(1).text(this.replytext);
				tds.eq(2).text(this.replyer);
				tds.eq(3).text(toDateString(this.regdate));
				tds.eq(4).text(toDateString(this.updatedate));
				$("#replyList").append(tr);
				tr.show();
			});
		});	
	}
	
	getReplyList();
	
	$("#btnReplyWrite").click(function() {
		let url = "/reply/insert";
		let sData = {
			"bno" : "${boardVo.bno}",
			"replytext" : $("#replytext").val(),
			"replyer" : $("#replyer").val()
		};
		
// 		$.post(url, sData, function(rData) {
// 			console.log(rData);
			// 보내는 데이터 형식이 json인 것을 지정해줄 수 없어서 동작하지 않음
// 		});

		$.ajax({
			"type" : "post",
			"url" : url,
			"headers" : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "post"
			},
			"dataType" : "text",
			"data" : JSON.stringify(sData),
			"success" : function(rData) {
				console.log(rData);
				if (rData == "success") {
					$("#replyList").find("tr:gt(0)").remove();
					// 삭제하지 않으면 계속 append됨
					getReplyList();
				}
			}
		});
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
	<br>
	
	<h4 style="margin-top:30px">Comments</h4>
	<!-- 댓글 입력 -->
	<div class="row">
		<div class="col-md-8">
			<input type="text" class="form-control" placeholder="내용"
				id="replytext">
		</div>
		<div class="col-md-3">
			<input type="text" class="form-control" placeholder="작성자"
				id="replyer">
		</div>
		<div class="col-md-1">
			<button type="button" class="btn btn-sm btn-primary"
				id="btnReplyWrite">입력</button>			
		</div>
	</div>
	
	<!-- 댓글 목록 -->
	<div class="row" style="margin-top:30px">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>댓글 내용</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>수정일</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="replyList">
					<tr style="display:none;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><button type="button" 
								class="btn btn-sm btn-warning btn-reply-update">
								수정</button></td>
						<td><button type="button" 
								class="btn btn-sm btn-danger btn-reply-delete">
								삭제</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>