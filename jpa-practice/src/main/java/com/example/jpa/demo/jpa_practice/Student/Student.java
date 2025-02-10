package com.example.jpa.demo.jpa_practice.Student;

import com.example.jpa.demo.jpa_practice.base.classes.BaseRefType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="students")
@ToString
@Getter
@Setter
public class Student extends BaseRefType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "second_name")
	private String secondName;
	@Column(name = "class")
	private int classStudent;
	@Column(name = "gender")
	private String gender;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile_number")
	private String mobileNumber;

}
