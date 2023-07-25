<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<script>
$(function() {
	// 창닫기, 새로고침시 이미 올려진 파일 삭제(비동기로 처리)
	var nowSubmit = false; // 전송버튼을 클릭하면 true로 변경
	$(window).bind("beforeunload", function() {
		if (!nowSubmit) {
			$(".uploadedItem").find("a").each(function() {
				var filename = $(this).attr("data-filename")
				$.ajax({
					"type" : "delete",
					"url" : "/upload/deleteAttach",
					"dataType" : "text",
					"data" : filename,
					"success" : function() {
					}
				});
			});
		}
	});	
	
	$("#uploadDiv").on("dragenter dragover", function(e) { // 영역 안으로 들어올 때 함수 실행
		e.preventDefault();
	});
	$("#uploadDiv").on("drop", function(e) { // 영역 안에 드롭할 때 함수 실행
		// 비동기로 파일 전송하기
		e.preventDefault();
		console.log(e);
		let file = e.originalEvent.dataTransfer.files[0]; // 파일 정보 얻기
		console.log(file);
		// <form enctype="multipart/form-data" action="/upload/uploadFile">
		// 	<input type="file" name="file">
		// </form> 의 역할을 하는 코드가 필요함
		let formData = new FormData(); // html5부터 사용
		formData.append("file", file); // file이라는 이름으로 file 전송
		let url = "/upload/uploadFile";
		$.ajax({
			"type" : "post",
			"processData" : false, // 파라미터 형식으로 보내지 않음
			"contentType" : false, // text데이터가 아닌 binary data임을 알림
			"url" : url,
			"data" : formData,
			"success" : function(rData) {
// 				console.log(rData);
				let div = $("#uploadedItem").clone();
				div.removeAttr("id").addClass("uploadedItem");
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
	$("#uploadedDiv").on("click", "div > a", function(e) {
		e.preventDefault();
// 		console.log("x");
		let that = $(this);
		let url = "/upload/deleteFile";
		let filename = that.attr("data-filename");
		$.ajax({
			"type" : "delete",
			"url" : url,
			"dataType" : "text",
			"data" : filename,
			// url에 파라미터를 안넣으면 json 타입 등으로 데이터를 보내야 함
			// url에 "/upload.deleteFile?filename=xxx"를 넣으면 여기에 data 설정할 필요 없음
			// data를 지정해서 보내면 Controller에서 @RequestBody로 데이터를 받아야 함
			"success" : function(rData) {
				console.log(rData);
				that.parent().remove();
			}
		});
	});
	
	let index = 0;
	
	// 폼 전송
	$("#myform").submit(function() { // 폼을 전송할 때 수행할 코드
		nowSubmit = true;
		$(".uploadedItem a").each(function() { // 반복문(for-each)
			let fullname = $(this).attr("data-filename");
			console.log(fullname);
			// controller로 보낼 데이터 작업
			// form태그에 hidden으로 filename을 보내는 태그 추가하기
			let inputTag = "<input type='hidden' name='files["+ (index++) +"]'";
			inputTag += " value='" + fullname + "'>";
			$("#myform").prepend(inputTag); // form 태그 내 앞부분에 추가하기
		});
// 		return false; // -> 전송을 하지 않음
	});
});
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글쓰기 양식</h2><br>
				<p><a href="/board/list" class="btn btn-success">글 목록으로 이동</a></p>
				<%-- a링크로 이동 : get방식 --%>
			</div>
			<form id="myform" role="form" action="/board/register" method="post">
<%-- 				enctype="multipart/form-data"> 파일처리(binary data)를 위한 데이터 타입 --%>
<!-- 				<input type="file" name="file"> -->
				<div class="form-group">
					<label for="title">제목</label> 
					<%-- lable for: id랑 같게 맞춤 --%>
					<input type="text" class="form-control" 
						id="title" name="title"/>
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" 
						id="content" name="content"></textarea>
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input type="text" class="form-control" 
						id="writer" name="writer"/>
				</div>
				
				<!-- 파일 업로드 공간 -->
				<div id="uploadDiv"></div>
				
				<!-- 복사(clone)할 때 사용할 div -->
				<div id="uploadedItem" style="display:none;">
					<img src="/images/default.png" height="100px"><br>
					<span>default</span><br>
					<a href="#">&times;</a>
				</div>
				
				<!-- 업로드된 파일 확인 -->
				<div id="uploadedDiv"></div>
				
				<div style="clear:both;"> <!-- uploadedItem에 설정된 float값 해제 -->
					<button type="submit" class="btn btn-primary" style="margin-top:20px;"> 
						작성완료
					</button>
				</div>
			</form>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>