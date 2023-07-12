package com.kh.ex02.vo;

import lombok.ToString;

@ToString
public class PagingDto {
	
	private int page; 			// 현재 페이지
	private int startRow; 		// 시작 행번호(rownum between A and B에서 A에 해당)
	private int endRow; 		// 끝 행번호(rownum between A and B에서 B에 해당)
	private int startPage; 		// 페이지 리스트의 첫 페이지. 1 2 3 ... 9 10에서 1
	private int endPage; 		// 페이지 리스트의 마지막 페이지. 1 2 3 ... 9 10에서 10
	private int perPage = 10; 	// 한 페이지 당 게시글 수(게시글 n개씩 보기)
	private int totalCount; 	// 전체 데이터 개수
	private int totalPage; 		// 전체 페이지 수
	private final int PAGE_BLOCK_COUNT = 10; 
	
	public PagingDto(int page, int count) {
		this.page = page;
		this.totalCount = count;
		calc();
	}
	
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public int getPage() {
		return page;
	}
	
	public void calc() {
		this.endRow = this.page * this.perPage;
		this.startRow = this.endRow - (this.perPage - 1);
		
		endPage = (int)(Math.ceil(page / (double)PAGE_BLOCK_COUNT)) * PAGE_BLOCK_COUNT;
		startPage = endPage - (PAGE_BLOCK_COUNT - 1);
		
		totalPage = (int)Math.ceil(totalCount / (double)perPage);
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

}
