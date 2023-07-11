<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글쓰기 양식</h2><br>
				<p><a href="/board/list" class="btn btn-success">글 목록으로 이동</a></p>
				<%-- a링크로 이동 : get방식 --%>
			</div>
			<form role="form" action="/board/register" method="post">
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
				<button type="submit" class="btn btn-primary">
					작성완료
				</button>
			</form>
		</div>
	</div>
</div>	

<%@ include file="/WEB-INF/views/include/footer.jsp" %>