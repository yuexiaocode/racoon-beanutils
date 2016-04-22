package racoon.beanutils.test.beans;

import java.util.List;

public class RpcStudent {
	
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private RpcTeacher teacher;
	
	private List<RpcTeacher> teachers;

	public List<RpcTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<RpcTeacher> teachers) {
		this.teachers = teachers;
	}

	public RpcTeacher getTeacher() {
		return teacher;
	}
	public void setTeacher(RpcTeacher teacher) {
		this.teacher = teacher;
	}

	
	
	
}
