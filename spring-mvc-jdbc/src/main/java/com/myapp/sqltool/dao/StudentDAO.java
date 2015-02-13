package com.myapp.sqltool.dao;

import java.util.List;

import com.myapp.sqltool.model.Student;

public interface StudentDAO 
{

	public List<Student> getAllstudents();

	public Student getOnestudents(int studentid);

	public boolean saveStudents(Student student);

	public boolean Updatestudents(Student student);
	
	public boolean deletestudents(int studentid);
}
