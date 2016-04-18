package racoon.beanutils.test.beans;

import java.util.List;
import java.util.Map;

public class RpcStudent {
	private int id;
	
	private List<RpcTeacher> teachers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<RpcTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<RpcTeacher> teachers) {
		this.teachers = teachers;
	}
	
	private Map<String,String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
}
