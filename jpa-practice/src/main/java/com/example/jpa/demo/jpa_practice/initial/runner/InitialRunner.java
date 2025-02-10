package com.example.jpa.demo.jpa_practice.initial.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jpa.demo.jpa_practice.Student.Student;
import com.example.jpa.demo.jpa_practice.Student.StudentRepository;
import com.github.javafaker.Faker;

//@Component
public class InitialRunner implements CommandLineRunner {

	StudentRepository studentRepository;
	Supplier<Student> studentSupplier = this::prepareStudent;

	public InitialRunner(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		int totalRecords = 5000000;
		System.out.println("Starting data generation...");
		generateStudents(totalRecords);
		System.out.println("Data generation completed!");
	}

	public void generateStudents(int count) {
		int batchSize = 10000;
		for (int i = 0; i < count; i += batchSize) {
			List<Student> students = new ArrayList<>();
			for (int j = 0; j < batchSize && i + j < count; j++) {
				students.add(studentSupplier.get());
			}
			studentRepository.saveAll(students);
			System.out.println("Inserted " + (i + batchSize) + " records so far...");
		}

	}

	public Student prepareStudent() {
		Student student = new Student();
		Faker faker = new Faker();
		student.setFirstName(faker.name().firstName());
		student.setSecondName(faker.name().lastName());
		student.setClassStudent(faker.number().numberBetween(1, 10));
		student.setEmail(faker.internet().emailAddress());
		student.setMobileNumber(faker.phoneNumber().phoneNumber());
		student.setGender(faker.demographic().sex());
		return student;
	}

}
