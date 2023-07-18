<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각 페이지 링크에 파라미터 설정(어떤 조건이든 페이징이 잘 되도록 하기 위함) -->
<form id="frmPaging" action="/board/list" method="get">
	<input type="hidden" name="bno" value="${param.bno}">
	<input type="hidden" name="page" value="${pagingDto.page}">
	<input type="hidden" name="perPage" value="${pagingDto.perPage}">
	<input type="hidden" name="searchType" value="${pagingDto.searchType}">
	<input type="hidden" name="keyword" value="${pagingDto.keyword}">
</form>