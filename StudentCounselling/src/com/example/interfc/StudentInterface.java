package com.example.interfc;

import java.util.List;

import com.example.entity.Student;

public abstract class StudentInterface {
	public abstract void saveOrUpdateStudent(Student student);
	public abstract void deleteStudent(int studentId);
	public abstract List<Student> getAllStudents();
	public abstract Student getStudentById(int studentId);
}
