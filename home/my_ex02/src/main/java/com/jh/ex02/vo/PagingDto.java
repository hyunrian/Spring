package com.jh.ex02.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PagingDto {
	
	private int nowPage = 1;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	private int perPage = 10;
	private int totalCount;
	private int totalPage;
	private final int PAGE_BLOCK_COUNT = 10;
	
	public PagingDto(int nowPage, int perPage, int totalCount) {
		this.nowPage = nowPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
		calc();
	}
	
	public void calc() {
		endRow = nowPage * perPage;
		startRow = endRow - (perPage - 1);
		
		/* 전체글 112개
		 * 페이지 블럭 10페이지씩
		 * 글 10개씩 보기
		 * 1페이지: 1~10개
		 * 2페이지: 11~20
		 * 3페이지: 21~30
		 * 10페이지: 91~100
		 * 11페이지: 101~110
		 * 12페이지: 111~112
		 * 1페이지 -> 1~10
		 * 2페이지 -> 1~10
		 * 10페이지 -> 1~10
		 * 11페이지 -> 11~20
		 * 
		 * 전체글 112개
		 * 페이지블럭 10페이지씩
		 * 글 5개씩 보기
		 * 1페이지: 1~5
		 * 2페이지: 6~10
		 * 3페이지: 11~15
		 * 10페이지: 46~50
		 * 11페이지: 51~55
		 * 20페이지: 96~100
		 * 21페이지: 101~105
		 * 22페이지: 106~110
		 * 23페이지: 111~112
		 * 
		 * */
		endPage = (int)(Math.ceil(nowPage / (double)PAGE_BLOCK_COUNT)) * PAGE_BLOCK_COUNT;
		startPage = endPage - (PAGE_BLOCK_COUNT - 1);

		totalPage = (totalCount / perPage) + 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
	}
	
}
