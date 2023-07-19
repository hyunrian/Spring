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
			// $.post / $.get은 form-data 형식으로만 데이터를 보낼 수 있음
// 		});

		/*
			웹?html?의 기본 구조가 header와 body로 나누어지는데
			header에서 컨텐트타입 등의 정의가 들어감
		*/	
		$.ajax({
			"type" : "post",
			"url" : url,
			"headers" : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "post"
				// "X-HTTP-Method-Override"가 없어도 크롬에서는 동작하지만
				// 모든 브라우저에서 다 가능한 것은 아님 -> 작성하는 것이 좋음 
			},
			"dataType" : "text",
			"data" : JSON.stringify(sData), // json데이터를 문자열로 변경
			"success" : function(rData) {
				console.log(rData);
				if (rData == "success") {
					$("#replyList").find("tr:gt(0)").remove();
					// 삭제하지 않으면 계속 append됨
					getReplyList();
				}
			}
		});
		
		$("#replytext").val("");
		$("#replyer").val("");
	});
	
// 	$(".btn-reply-update").click(function() {
// 		console.log("update clicked");
// 	}); 
	// 로딩되기 전에 여기 부분을 읽는데, 그 때는 처음 tr이 아닌 나머지 tr의 btn-reply-update 버튼을 
	// 찾을 수 없기 때문에 상위 엘리먼트를 기준으로 찾아야 함
	
	// 변수를 사용하지 않고 <span style="display:none">을 만들어 그 안에 각 댓글의 값을 넣어도 됨

	var flag = 0;
	var prevBtn = null;
	
	$("#replyList").on("click", ".btn-reply-update", function() {
		var that = $(this);
		flag++;
		console.log(flag);
		if (flag > 1) {
			$(".btn-reply-update").show().attr("data-status", "default");
			$(".btn-reply-update-completed").hide();
			let val = $(".replytext").find("input");
			let dd = $(".btn-reply-update[data-status='default']");
			if (prevBtn != null) {
				console.log(prevBtn);
				let prevTd = prevBtn.parent().parent().find("td").eq(1);
				let prevText = prevTd.find("input").val();
				prevTd.text(prevText);
				
				let prevTd2 = prevTd.next();
				let prevWriter = prevTd2.find("input").val();
				prevTd2.text(prevWriter);
			}
			--flag;
			console.log("flag:" + flag);
			prevBtn = $(this);
		} else {
			prevBtn = $(this);
		}
		
		$(this).hide().attr("data-status", "updating");
// 		let btnComplete = $(this).parent().find("button").eq(1);
		let btnComplete = $(this).next();
		btnComplete.show();
		let replytext = $(this).parent().parent().find("td").eq(1);
		let content = replytext.text();
		replytext.html("<input type='text' value='" + content + "'>");
		
		let replywriter = $(this).parent().parent().find("td").eq(2);
		let name = replywriter.text();
		replywriter.html("<input type='text' value='" + name + "'>");
		
		let rno = $(this).parent().parent().find("td").eq(0);
		
		console.log($(this).next());
		
	});
	
	// 버튼1의 click 이벤트 안에 버튼2의 click 이벤트를 설정하면
	// 버튼1을 누를 때마다 버튼 2의 클릭이벤트 횟수가 누적되어
	// 버튼2를 클릭했을 때 해당 횟수만큼 실행됨 -> 주의!
	$("#replyList").on("click", ".btn-reply-update-completed", function() {
		let replytext = $(this).parent().parent().find("td").eq(1);
		let replywriter = $(this).parent().parent().find("td").eq(2);
		let rno = $(this).parent().parent().find("td").eq(0);
		let sData = {
				"replytext" : replytext.find("input").val(),
				"replyer" : replywriter.find("input").val(),
				"rno" : rno.text()
		};
		$.ajax({
			"type" : "patch",
			"url" : "/reply/update",
			"headers" : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "patch"
			},
			"dataType" : "text",
			"data" : JSON.stringify(sData), // json데이터를 문자열로 변경
			"success" : function(rData) {
				console.log(rData);
				if (rData == "success") {
					$("#replyList").find("tr:gt(0)").remove();
					console.log("removed");
					getReplyList();
				}
			}
		});
		flag = 0;
		});
	
	$("#replyList").on("click", ".btn-reply-delete", function() {
		var rno = $(this).parent().parent().find("td").eq(0).text();
		$.ajax({
			"type" : "delete",
			"url" : "/reply/delete/" + rno,
			"data" : rno,
			"success" : function(rData) {
				console.log(rData);
				if (rData == "success") {
					$("#replyList").find("tr:gt(0)").remove();
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
						<td class="replytext"></td>
						<td class="replyer"></td>
						<td></td>
						<td></td>
						<td><button type="button" 
								class="btn btn-sm btn-warning btn-reply-update">수정</button>
							<button type="button" style="display:none;"
								class="btn btn-sm btn-warning btn-reply-update-completed">수정완료</button></td>
						<td><button type="button" 
								class="btn btn-sm btn-danger btn-reply-delete">삭제</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>