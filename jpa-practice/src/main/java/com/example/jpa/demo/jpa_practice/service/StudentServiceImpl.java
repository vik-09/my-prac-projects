package com.example.jpa.demo.jpa_practice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.jpa.demo.jpa_practice.Student.Student;
import com.example.jpa.demo.jpa_practice.Student.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Page<Student> getStudentByName(String name, Pageable pageable) {
		return studentRepository.searchByName(name, pageable);

	}

//	@Override
//	public Page<Student> getStudentByFirstNameOrSecondNameOrMobileNumber(String name, Pageable pageable) {
//		//return studentRepository.findBySearchInput(name, pageable);
//	}

//	@Override
//	public List<Student> getStudentByFirstNameOrSecondName(String name, Pageable pageable) {
//	//	return studentRepository.findByFirstNameContainingOrLastNameContaining(name, pageable);
//	}

//	public void getStudentByFirstNameOrSecondNameOrMobileNumber(String searchParam, Pageable pageable) {
//	}

}
