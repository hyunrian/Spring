package spring.test.jh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.test.jh.vo.StudentVo;

@Repository
public class StudentDao {
	
	@Autowired
	SqlSession sqlSession;
	
	// 입력
	public void insert(StudentVo studentVo) {
		sqlSession.insert("mymapper.insert", studentVo);
	}
	
	// 조회
	public List<StudentVo> selectAll() {
		List<StudentVo> list = sqlSession.selectList("mymapper.selectAll");
		return list;
	}
	
	// 수정
	public void update(StudentVo StudentVo) {
		sqlSession.update("mymapper.update", StudentVo);
	}
	
	// 1명 정보 조회
	public StudentVo selectOne(int sno) {
		StudentVo vo = sqlSession.selectOne("mymapper.selectOne", sno);
		return vo;
	}
	
	
	// 삭제
	public void delete(int sno) {
		sqlSession.delete("mymapper.delete", sno);
	}

}
