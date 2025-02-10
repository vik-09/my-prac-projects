package com.example.jpa.demo.jpa_practice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.jpa.demo.jpa_practice.Student.Student;

public interface StudentService {

	public Page<Student> getStudentByName(String name, Pageable pageable);

	//Page<Student> getStudentByFirstNameOrSecondNameOrMobileNumber(String name, Pageable pageable);

//	public List<Student> getStudentByFirstNameOrSecondName(String name, Pageable pageable);

}
