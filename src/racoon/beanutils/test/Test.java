package racoon.beanutils.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import racoon.beanutils.YXBeanUtils;
import racoon.beanutils.test.beans.RpcStudent;
import racoon.beanutils.test.beans.RpcTeacher;
import racoon.beanutils.test.beans.Student;



public class Test {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException {
		
		RpcStudent rpcStudent = new RpcStudent();
		Map<String,String> map = new HashMap<String,String>();
		map.put("tttt", "tt");
		
		rpcStudent.setMap(map);
		
		
		RpcTeacher rpcTeacher = new RpcTeacher();
		rpcTeacher.setBirthday(System.currentTimeMillis());
		List<RpcTeacher> rpcTeachers = new ArrayList<RpcTeacher>();
		
		List<RpcStudent> students = new ArrayList<RpcStudent>();
		students.add(new RpcStudent());
		
		rpcTeacher.setStudents(students);
		
		rpcTeachers.add(rpcTeacher);
		rpcStudent.setTeachers(rpcTeachers);
		
		Student student = new Student();
		rpcStudent.setTeachers(rpcTeachers);
		
		
		
		//G<List<String>> gs = new G<List<String>>();
		
		//Map<String,Class<?>> convertMap = new HashMap<String, Class<?>>();
		//convertMap.put("t", List.class);
		YXBeanUtils.copyProperties(student,rpcStudent);
	
		//Date d= student.getTeachers().get(0).getBirthday();
		//System.out.println(d);
		
		
	}
}
