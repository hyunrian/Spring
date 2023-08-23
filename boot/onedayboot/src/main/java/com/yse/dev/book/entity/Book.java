package com.yse.dev.book.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder // @AllArgConstructor 자동으로 생성됨
@NoArgsConstructor // -> 기본생성자가 자동으로 만들어지지 않으므로 생성 
@AllArgsConstructor // -> 기본생성자가 만들어지면 자동 생성되었던 all이 사라지므로 다시 생성
public class Book {

	@Id // == primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sequence를 만들지 않아도 알아서 관리가 됨(IDENTITY -> auto increment)
	private Integer bookId;
	
	@Column(length = 200) // default 255
	private String title;
	
	private Integer price;
	
	@CreationTimestamp // sysdate로 자동 생성
	private LocalDateTime insertDateTime;
}
