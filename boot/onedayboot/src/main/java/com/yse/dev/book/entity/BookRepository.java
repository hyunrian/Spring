package com.yse.dev.book.entity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{ // <entity type, entity pk(id) type>

	// JpaRepository를 상속받아 자동으로 구현되는 것들이 있음
	
	public List<Book> findByTitleContains(String title, Pageable pageable);
}
