package com.yse.dev.book.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yse.dev.book.dto.BookCreateDTO;
import com.yse.dev.book.dto.BookEditDTO;
import com.yse.dev.book.dto.BookEditResponseDTO;
import com.yse.dev.book.dto.BookListResponseDTO;
import com.yse.dev.book.dto.BookReadResponseDTO;
import com.yse.dev.book.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	// 입력 페이지로 이동
	@GetMapping("/book/create") // get방식으로 mapping
	public String create() {
		return "book/create"; // -> templates/book/create.html
	}
	
	// 입력 처리
	@PostMapping("/book/create") // post방식으로 mapping
	public String create(BookCreateDTO bookCreateDTO) {
		Integer bookId = bookService.insert(bookCreateDTO);
		return String.format("redirect:/book/read/%s", bookId); // 상세보기 화면으로 redirect
		// String.format을 사용하여 가독성을 높임(+로 문자열 연결 대신 사용)
	}
	
	// ModelAndView : model과 view를 함께 지정 가능
	// 원래 했던 방식인 model.addattribute를 해도 상관 없음
	@GetMapping("/book/read/{bookId}")
	public ModelAndView read(@PathVariable Integer bookId)
			throws NoSuchElementException {
		
		ModelAndView mav = new ModelAndView();
		BookReadResponseDTO bookReadResponseDTO = bookService.read(bookId);
		mav.addObject("bookReadResponseDTO", bookReadResponseDTO);
		mav.setViewName("book/read"); // book폴더의 read.html로 view 지정
		
		return mav;
	}
	
	@GetMapping("/book/edit/{bookId}")
	public ModelAndView edit(@PathVariable Integer bookId)
			throws NoSuchElementException {
		
		ModelAndView mav = new ModelAndView();
		BookEditResponseDTO bookEditResponseDTO = bookService.edit(bookId);
		mav.addObject("bookEditResponseDTO", bookEditResponseDTO);
		mav.setViewName("book/edit");
		return mav;
	}
	
	@PostMapping("/book/edit/{bookId}")
	public ModelAndView update(@Validated BookEditDTO bookEditDTO, // BookEditDTO에서 설정한 유효성 검사 
			Errors errors) { // 에러가 있다면 에러 호출
		
		if (errors.hasErrors()) { // 유효성 검사에 대해 에러가 있는 경우
			
			List<FieldError> errorList = errors.getFieldErrors();
			StringBuffer sb = new StringBuffer();
			
			for (FieldError fieldError : errorList) {
				String field = fieldError.getField();
				String message = fieldError.getDefaultMessage();
				sb.append(field + " : " + message + "\n");
			}
			
			return this.error422(sb.toString(), 
					String.format("/book/edit/%s", bookEditDTO.getBookId()));
		}
		
		bookService.update(bookEditDTO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/book/read/%s", 
				bookEditDTO.getBookId()));
		return mav;
	}
	
	@PostMapping("/book/delete")
	public String delete(Integer bookId) 
			throws NoSuchElementException {
		
		bookService.delete(bookId);
		return "redirect:/book/list";
	}
	
	@GetMapping({"/book/list", "/book"}) // 여러개의 url을 처리할 때는 {} 사용
	public ModelAndView bookList(String title, Integer page, ModelAndView mav) {
		mav.setViewName("book/list");
		List<BookListResponseDTO> books = bookService.bookList(title, page);
		mav.addObject("books", books);
		return mav;
	}

	// 해당 컨트롤러 내에서 발생할 예외를 처리할 메서드
	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHandler(
			NoSuchElementException ex) {
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", "책 정보가 없습니다."); // 사용자에게 알릴 메세지
		mav.addObject("location", "/book"); // 이동할 경로
		mav.setViewName("common/error/422");
		return mav;
	}
	
	private ModelAndView error422(String message, String location) {
		
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", message);
		mav.addObject("location", location);
		mav.setViewName("common/error/422");
		return mav;
	}
	
}
