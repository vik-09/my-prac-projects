package com.example.jpa.demo.jpa_practice.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.demo.jpa_practice.Student.Student;
import com.example.jpa.demo.jpa_practice.service.StudentService;

@RestController
public class StudentController {

	StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping(path = "/student", produces = "application/json")
	public Page<Student> getStudentsByName(@RequestParam String name) {
		Sort sort = Sort.by(Direction.ASC, "secondName");
		PageRequest pageRequest = PageRequest.of(0, 10, sort);
		// return studentService.getStudentByName(name, pageRequest);
		return studentService.getStudentByName(name, pageRequest);
	}

}
