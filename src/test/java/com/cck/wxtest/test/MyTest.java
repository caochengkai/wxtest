package com.cck.wxtest.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.sourceforge.plastosome.json.JSON;


public class MyTest {
	@org.junit.Test
	public void testJson() throws Exception {
		User user=new User();
		user.setName("User");
		Teacher teacher=new Teacher();
		Student student = new Student();
		student.setName("student");
		teacher.setStudent(student);
		user.setTeacher(teacher);
		JSON.serialize(new FileWriter(new File("c:/temp/User.json")), user);
		User javaObject = (User)JSON.toJavaObject(JSON.deserialize(new FileReader(new File("c:/temp/User.json"))), User.class);
		System.out.println(javaObject);
	}
}
