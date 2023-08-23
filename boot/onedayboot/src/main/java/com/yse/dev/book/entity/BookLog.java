package com.yse.dev.book.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment 적용
	private Integer bookLogId;
	
	@ManyToOne(fetch = FetchType.LAZY) // BookLog가 여러개, Book이 1개. default값이 LAZY - 필요한 경우에 사용됨
	@JoinColumn(name = "book_id") // 실제 생성된 컬럼명을 작성
	private Book book;
	
	@Column(columnDefinition = "TEXT") // CLOB 타입(Character Large - 큰 텍스트 데이터)
	private String comment; // varchar(255)
	
	private Integer page; // 책의 페이지
	
	@CreationTimestamp
	private LocalDateTime insertDateTime;
}
