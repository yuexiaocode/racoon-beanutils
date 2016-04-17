package racoon.beanutils.test.beans;

import java.util.List;
import java.util.Map;

public class Student {
	private String id;
	private List<Teacher> teachers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	private Map<String,Integer> map;
	
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
}
