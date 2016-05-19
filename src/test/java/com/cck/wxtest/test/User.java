package com.cck.wxtest.test;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private Teacher teacher;

	

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
