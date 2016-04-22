package racoon.beanutils.test.beans;

import java.util.List;


public class RpcTeacher {

	
	public List<RpcStudent> students;
	public List<RpcStudent> getStudents() {
		return students;
	}
	public void setStudents(List<RpcStudent> students) {
		this.students = students;
	}
	
	private RpcStudent student;
	public RpcStudent getStudent() {
		return student;
	}
	public void setStudent(RpcStudent student) {
		this.student = student;
	}
	
}
