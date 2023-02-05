package com.gl.studentmanagement.service;

import java.util.List;

import com.gl.studentmanagement.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student getStudentById(Long id);

	Student saveStudent(Student student);

	void deleteStudentById(Long id);

}