package spring.test.jh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.test.jh.service.StudentService;
import spring.test.jh.vo.StudentVo;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertGet() {
		
		return "student/insert";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(StudentVo studentVo) {
		service.insert(studentVo);
		
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String selectAll(Model model) {
		List<StudentVo> list = service.selectAll();
		model.addAttribute("list", list);
		return "student/list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(StudentVo studentVo) {
		System.out.println("vo:" + studentVo);
		service.update(studentVo);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String selectOne(int sno, Model model) {
		StudentVo vo = service.selectOne(sno);
		model.addAttribute("studentVo", vo);
		return "student/update";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int sno) {
		service.delete(sno);
		return "redirect:/student/list";
	}
	

}
