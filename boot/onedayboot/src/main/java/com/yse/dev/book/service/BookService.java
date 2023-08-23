package com.yse.dev.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.yse.dev.book.dto.BookCreateDTO;
import com.yse.dev.book.dto.BookEditDTO;
import com.yse.dev.book.dto.BookEditResponseDTO;
import com.yse.dev.book.dto.BookListResponseDTO;
import com.yse.dev.book.dto.BookReadResponseDTO;
import com.yse.dev.book.entity.Book;
import com.yse.dev.book.entity.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	
	// final로 선언한 BookRepository를 파라미터로 넣은 생성자를 정의하면 Autowired를 안해도 됨
	// 생성자를 통해 주입받는 방식(DI)이 spring이 권장하는 방식임
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Integer insert(BookCreateDTO bookCreateDTO) {
		
		// builder를 이용한 Book 객체 생성
		Book book = Book.builder()
				.title(bookCreateDTO.getTitle()) // setTitle()과 같음
				.price(bookCreateDTO.getPrice())
				.build();
		
		bookRepository.save(book); // repository에 객체 저장(table에 변화를 감지하여 데이터를 저장함)
		return book.getBookId();
	}
	
	public BookReadResponseDTO read(Integer bookId)
			throws NoSuchElementException {
		
		Book book = bookRepository.findById(bookId).orElseThrow(); // id를 찾고, 없으면 예외 발생시킴
		BookReadResponseDTO bookReadResponseDTO = 
				BookReadResponseDTO.BookFactory(book);

		return bookReadResponseDTO;
	}
	
	public BookEditResponseDTO edit(Integer bookId)
			throws NoSuchElementException {
		

		Book book = bookRepository.findById(bookId).orElseThrow(); // id를 찾고, 없으면 예외 발생시킴
		BookEditResponseDTO bookEditResponseDTO = 
				BookEditResponseDTO.BookFactory(book);

		return bookEditResponseDTO;
	}
	
	public void update(BookEditDTO bookEditDTO)
			throws NoSuchElementException {
		
		Book book = bookRepository
				.findById(bookEditDTO.getBookId())
				.orElseThrow();
		book = bookEditDTO.fill(book); // BookEditDTO에서 정의한 메서드
		bookRepository.save(book);
	}
	
	public void delete(Integer bookId) throws NoSuchElementException {
		
		Book book = bookRepository.findById(bookId).orElseThrow();
		bookRepository.delete(book);
	}
	
	public List<BookListResponseDTO> bookList(String title, Integer page) {
		
		final int pageSize = 3; // 한페이지 당 볼 데이터 개수
		List<Book> books = null;
		
		if (page == null) {
			page = 0; // pageable은 0페이지부터 시작함
		} else {
			page -= 1;
		}
		
		if (title == null) { // 검색을 하지 않은 경우
			System.out.println("null");
			Pageable pageable = 
					PageRequest.of(page, pageSize, 
							Direction.DESC, "insertDateTime"); // 인서트한 데이터 역순
			books = bookRepository.findAll(pageable).toList();
		} else {
			System.out.println("null2");
			Pageable pageable = PageRequest.of(page, pageSize);
			Sort sort = Sort.by(Order.desc("insertDateTime"));
			pageable.getSort().and(sort); // 현재 정렬된 내용에 sort를 적용함
			books = bookRepository.findByTitleContains(title, pageable);
		}
		
		List<BookListResponseDTO> list = new ArrayList<>();
		for (Book book : books) {
			BookListResponseDTO dto = 
					new BookListResponseDTO(book.getBookId(), book.getTitle());
			list.add(dto);
		}
		
		return list;
	} 
	
}
