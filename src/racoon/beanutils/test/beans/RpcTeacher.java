package racoon.beanutils.test.beans;

import java.util.List;


public class RpcTeacher {
	private int id;
	private long birthday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	
	public List<RpcStudent> students;
	public List<RpcStudent> getStudents() {
		return students;
	}
	public void setStudents(List<RpcStudent> students) {
		this.students = students;
	}
	
	
}
