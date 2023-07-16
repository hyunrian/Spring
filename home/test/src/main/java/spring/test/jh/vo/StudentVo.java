package spring.test.jh.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StudentVo {

	private int sno;
	private String sname;
	private String sgender;
	private String smajor;
	private int sscore;
	
}
