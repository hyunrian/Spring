<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<script>
$(function() {

	var isLiked = false;
	
	$.ajax({
		"type" : "post",
		"url" : "/like/isLiked/" + ${boardVo.bno},
		"success" : function(rData) {
			if (rData == 1) { // 좋아요 했던 페이지일 때
				$("#likeHeart").attr("style", "font-size:48px; color:red;");
				isLiked = true;
			}
		}
	});
	
// 	$.post("/like/isLiked", function(rData) {
// 		console.log("페이지 로딩 때 rData:", rData);
// 		if (rData == 1) { // 좋아요 했던 페이지일 때
// 			$("#likeHeart").attr("style", "font-size:48px; color:red;");
// 			isLiked = true;
// 		}
// 	});
	
	$.post("/like/count/${boardVo.bno}", function(rData) {
		console.log("좋아요 개수: ", rData);
		$("#likeHeart").next().text(rData);
	});
	
	// 좋아요
	$("#likeHeart").click(function() {
		console.log("heart 클릭됨");
		let that = $(this);
		let bno = ${boardVo.bno};
		$.ajax({
			"url" : "/like/isLiked/" + bno,
			"type" : "post",
			"success" : function(rData) {
				if (rData == 1) { // 좋아요해놨던 상태일 때
					let url = "/like/dislike/" + bno;
					$.ajax ({
						"url" : url,
						"type" : "delete",
						"dataType" : "text",
						"success" : function(rData) {
							that.next().text(rData);
							that.attr("style", "font-size:48px; color:#3399EE;");
						}
					});
				} else { // 좋아요하지 않았던 상태일 때
					let url = "/like/heart";
					let bno = ${boardVo.bno};
					console.log("bno:", bno);
					$.ajax ({
						"url" : url,
						"data" : {"bno" : bno},
						"type" : "post",
						"dataType" : "text",
						"success" : function(rData) {
							that.next().text(rData);
							that.attr("style", "font-size:48px; color:red;");
						}
					});
				}
			}
		});
		/*
		if (!isLiked) { // 좋아요하지 않았던 상태일 때
			let url = "/like/heart";
			let bno = ${boardVo.bno};
			console.log("bno:", bno);
			$.ajax ({
				"url" : url,
				"data" : {"bno" : bno},
				"type" : "post",
				"dataType" : "text",
				"success" : function(rData) {
// 					console.log(rData);
					that.attr("style", "font-size:48px; color:red;");
				}
			});
		} else { // 좋아요해놨던 상태일 때
			let bno = ${boardVo.bno};
			let url = "/like/dislike/" + bno;
			$.ajax ({
				"url" : url,
				"type" : "delete",
				"dataType" : "text",
				"success" : function(rData) {
					console.log(rData);
					that.attr("style", "font-size:48px; color:#3399EE;");
				}
			});
		}
		*/
	});
	
	<!-- 첨부파일 리스트 -->
	$.getJSON("/board/getAttachList/${boardVo.bno}", function(rData) {
// 		console.log(rData); // 값이 여러개라 배열로 들어옴
// 		let index = 0; -> $.each에서 index를 받아올 수 있으므로 따로 사용할 필요 없음
		let filename = [];
		$.each(rData, function(index) { // 각각 넘어오는 데이터를 반복적으로 처리. .each일 때 function에 index를 받아올 수 있음
			div = $("#uploadedItem").clone();
			div.removeAttr("id").addClass("uploadedItem");
			div.find("a").hide().attr("data-filename", rData[index]);
			filename[index] = 
				rData[index].toString().substring(rData[index].indexOf("_") + 1);
			div.find("span").text(filename[index]);
			if (isImage(filename[index])) {
				let thumbnail = getThumbnailName(rData[index]);
				div.find("img").attr("src", "/upload/displayImage?thumbnail=" + thumbnail);
			}
// 			index++;
			div.show();
			$("#uploadedDiv").append(div);
		});
	});
	
	$("#btnModify").click(function() {
		<!-- prop: 값이 true/false. 단독으로 사용되는 경우 ex) checked / 그 외: attr  -->
		$(".readonly").prop("readonly", false);
		$("#btnModifyFinish").fadeIn(1000);
		$(this).fadeOut(1000);
		
		// 첨부파일 영역
		$("#uploadDiv").slideDown(400);
		
		// 첨부파일 삭제 링크
		$(".uploadedItem a").show();
	});
	
	$("#uploadDiv").on("dragenter dragover", function(e) {
		e.preventDefault();
	});
	
	// 첨부파일 추가
	$("#uploadDiv").on("drop", function(e) {
		e.preventDefault();
		let file = e.originalEvent.dataTransfer.files[0];
		console.log(file);
		let formData = new FormData();
		formData.append("file", file);
		let url = "/upload/uploadFile";
		$.ajax({
			"type" : "post",
			"processData" : false,
			"contentType" : false,
			"data" : formData,
			"dataType" : "text",
			"url" : url,
			"success" : function(rData) {
				console.log(rData);
				let div = $("#uploadedItem").clone();
				div.removeAttr("id").addClass("uploadedItem");
				div.addClass("newUpload");
				let filename = rData.substring(rData.indexOf("_") + 1);
				div.find("span").text(filename);
				if (isImage(filename)) {
					let thumbnail = getThumbnailName(rData);
					div.find("img").attr("src", "/upload/displayImage?thumbnail=" + thumbnail);
				}
				div.find("a").attr("data-filename", rData);
				$("#uploadedDiv").append(div);
				div.show();
			}
		});
		
	});
	
	// 첨부파일 삭제
	$("#uploadedDiv").on("click", "a", function(e) {
		e.preventDefault();
		let that = $(this);
		let filename = that.attr("data-filename");
		console.log(filename);
		let url = "/upload/deleteAttach";
		$.ajax({
			"type" : "delete",
			"data" : filename,
			"url" : url,
			"success" : function(rData) {
				console.log(rData);
				that.parent().remove();
			}
		});
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
				
				if ("${loginInfo.u_id}" != this.replyer) { // 댓글 작성자와 동일한 댓글일 때 수정, 삭제 버튼 보이기
					tds.eq(5).empty(); // remove -> tds.eq(4)를 삭제, empty -> td 안의 태그를 삭제
					tds.eq(6).empty();
				}
				$("#replyList").append(tr);
				tr.show();
			});
		});	
	}
	
	getReplyList();
	
	// 댓글 입력 버튼
	$("#btnReplyWrite").click(function() {
		let url = "/reply/insert";
		let sData = {
			"bno" : "${boardVo.bno}",
			"replytext" : $("#replytext").val()
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
	
	// 댓글 수정
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
				
// 				let prevTd2 = prevTd.next();
// 				let prevWriter = prevTd2.find("input").val();
// 				prevTd2.text(prevWriter);
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
		
// 		let replywriter = $(this).parent().parent().find("td").eq(2);
// 		let name = replywriter.text();
// 		replywriter.html("<input type='text' value='" + name + "'>");
		
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
	
	// 댓글 삭제
	$("#replyList").on("click", ".btn-reply-delete", function() {
		var rno = $(this).parent().parent().find("td").eq(0).text();
		$.ajax({
			"type" : "delete",
			"url" : "/reply/delete/" + rno + "/${boardVo.bno}",
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
	
	// 폼 전송
	$("#myform").submit(function() {
		$(".newUpload").each(function(i) {
			let html = "<input type='hidden' name='files[" + i + "]' value='";
			html += $(this).find("a").attr("data-filename") + "'>";
			$("#myform").prepend(html);
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
			<div>
				<form id="myform" role="form" action="/board/mod" method="post">
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
<!-- 					<div class="form-group"> -->
<!-- 						<label for="writer">작성자</label> -->
<!-- 						<input type="text" class="form-control readonly"  -->
<%-- 							id="writer" name="writer" value="${boardVo.writer}" readonly/> --%>
<!-- 					</div> -->

					<%-- 로그인 사용자 아이디, 작성자 아이디 비교해서 같으면 출력 --%>
					<c:if test="${loginInfo.u_id == boardVo.writer}">
						<div>
							<button type="button" class="btn btn-warning" id="btnModify">
								수정
							</button>
							<button type="submit" class="btn btn-primary" 
								id="btnModifyFinish" style="display:none;">
								수정완료
							</button>
							<a href="/board/delete?bno=${boardVo.bno}" class="btn btn-danger" id="btnDelete">
								삭제
							</a>
						</div>
					</c:if>
				</form>									
			</div><br>
			
			<!-- 파일 업로드 공간 -->
			<div id="uploadDiv" style="display:none;"></div>
			
			<!-- 첨부파일 목록 -->
			<div id="uploadedItem" style="display:none;">
					<img src="/images/default.png" height="100px"><br>
					<span>default</span><br>
					<a href='#'>&times;</a>
			</div>
			
			<!-- 브라우저에서 보일 첨부파일리스트 div -->
			<div id="uploadedDiv"></div>
			
		</div>
	</div>
	<br>
	
	<!-- 좋아요 -->
	<div class="text-center">
		<i class="fa fa-heart" style="font-size:48px;color:#3399EE;
			cursor:pointer;" id="likeHeart"></i>
		<span style="font-size:20px;">10</span>
	</div><br>
	
	
	<h4 style="margin-top:50px">Comments</h4>
	
	<!-- 댓글 입력 -->
	<div class="row">
		<div class="col-md-11">
			<input type="text" class="form-control" placeholder="내용"
				id="replytext">
		</div>
		<div class="col-md-1">
			<button type="button" class="btn btn-sm btn-primary"
				id="btnReplyWrite">입력</button>			
		</div>
	</div>
	
	<!-- 댓글 목록 -->
	<div id="replyListDiv" class="row" style="margin-top:30px">
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
						<td>
							<button type="button" 
								class="btn btn-sm btn-warning btn-reply-update">수정</button>
							<button type="button" style="display:none;"
								class="btn btn-sm btn-warning btn-reply-update-completed">수정완료</button>
						</td>
						<td>
							<button type="button" 
								class="btn btn-sm btn-danger btn-reply-delete">삭제</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>