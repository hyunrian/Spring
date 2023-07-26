<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp"%>

<script>
	$(function() {
		let loginResult = "${loginResult}";
		if (loginResult == "FAIL") {
			$("#loginFailMessage").slideDown(600);
		}

	});
</script>

<div class="container-fluid">
	<div class="row" style="display:none;" id="loginFailMessage">
		<div class="col-md-12">
			<div class="alert alert-danger">
				<strong>로그인 실패!</strong> 아이디와 비밀번호를 확인해주세요.
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="card card-info">
				<div class="card-header">
					<h3 class="card-title">로그인</h3>
				</div>
				<form class="form-horizontal" action="/user/login" method="post">
					<div class="card-body">
						<div class="form-group row">
							<label for="u_id" class="col-sm-2 col-form-label">아이디</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="u_id" id="u_id"
									placeholder="ID">
							</div>
						</div>
						<div class="form-group row">
							<label for="u_pw" class="col-sm-2 col-form-label">패스워드</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" name="u_pw"
									id="u_pw" placeholder="Password">
							</div>
						</div>
						<div class="form-group row">
							<div class="offset-sm-2 col-sm-10">
								<div class="useCookie">
									<input type="checkbox" class="form-check-input"
										name="useCookie" id="useCookie"> <label
										class="form-check-label" for="useCookie">아이디 기억하기</label>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer">
						<button type="submit" class="btn btn-info">Sign in</button>
						<a href="/" class="btn btn-default float-right">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>