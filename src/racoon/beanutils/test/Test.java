package racoon.beanutils.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import racoon.beanutils.YXBeanUtils;
import racoon.beanutils.test.beans.RpcStudent;
import racoon.beanutils.test.beans.RpcTeacher;
import racoon.beanutils.test.beans.Student;



public class Test {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException {
		
		RpcStudent rpcStudent = new RpcStudent();
		RpcTeacher rpcTeacher = new RpcTeacher();
		
		rpcStudent.setTeacher(rpcTeacher);
		
		rpcTeacher.setStudent(rpcStudent);
		
		List<RpcStudent> students = new ArrayList<RpcStudent>();
		students.add(rpcStudent);
		rpcTeacher.setStudents(students);
		
		
		
		Student student = new Student();
		
		
		
		/*RpcTeacher rpcTeacher = new RpcTeacher();
		List<RpcTeacher> rpcTeachers = new ArrayList<RpcTeacher>();
		
		List<RpcStudent> students = new ArrayList<RpcStudent>();
		students.add(rpcStudent);
		
		rpcTeacher.setStudents(students);
		
		rpcTeachers.add(rpcTeacher);
		rpcStudent.setTeachers(rpcTeachers);
		
		Student student = new Student();
		rpcStudent.setTeachers(rpcTeachers);*/
		
		
		
		//G<List<String>> gs = new G<List<String>>();
		
		//Map<String,Class<?>> convertMap = new HashMap<String, Class<?>>();
		//convertMap.put("t", List.class);
		YXBeanUtils.copyProperties(student,rpcStudent);
		YXBeanUtils.copyProperties(student,rpcStudent);
		System.out.println(student.getTeacher().getStudent().getTeacher()==student.getTeacher());
		//Date d= student.getTeachers().get(0).getBirthday();
		//System.out.println(d);
		
		
	}
}
