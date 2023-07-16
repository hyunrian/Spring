package spring.test.jh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.test.jh.dao.StudentDao;
import spring.test.jh.vo.StudentVo;

@Service
public class StudentService {
	
	@Autowired
	StudentDao dao;
	
	// 입력
	public void insert(StudentVo studentVo) {
		dao.insert(studentVo);
	}
	
	// 조회
	public List<StudentVo> selectAll() {
		List<StudentVo> list = dao.selectAll();
		return list;
	}
	
	// 수정
	public void update(StudentVo studentVo) {
		dao.update(studentVo);
	}
	
	// 1명 정보 조회
	public StudentVo selectOne(int sno) {
		StudentVo vo = dao.selectOne(sno);
		return vo;
	}
	
	// 삭제
	public void delete(int sno) {
		dao.delete(sno);
	}

}
