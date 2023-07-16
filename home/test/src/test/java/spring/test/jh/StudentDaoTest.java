package spring.test.jh;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.test.jh.dao.StudentDao;
import spring.test.jh.vo.StudentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class StudentDaoTest {

	@Autowired
	StudentDao dao;
	
	@Test
	public void testSelectAll() {
		List<StudentVo> list = dao.selectAll();
		for (StudentVo vo : list) {
			System.out.println(vo);
		}
	}
	
	@Test
	public void testInsert() {
		StudentVo vo = new StudentVo();
		vo.setSname("1");
		vo.setSgender("M");
		vo.setSmajor("2");
		vo.setSscore(88);
		dao.insert(vo);
	}
	
	@Test
	public void testUpdate() {
		StudentVo vo = dao.selectOne(2);
		System.out.println(vo);
		vo.setSname("우끼");
		vo.setSgender("F");
		vo.setSscore(77);
		vo.setSmajor("피아노");
		dao.update(vo);
	}
	
	@Test
	public void testDelete() {
		dao.delete(3);
	}
	
}
