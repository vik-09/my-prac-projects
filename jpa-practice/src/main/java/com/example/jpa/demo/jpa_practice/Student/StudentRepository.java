package com.example.jpa.demo.jpa_practice.Student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	@Query("select s1 from students s1 where s1.firstName like :name or s1.secondName like :name")
	public Page<Student> searchByName(@Param("name") String name, Pageable pageable);

	// public List<Student> findByFirstNameContainingOrLastNameContaining(String
	// name, Pageable pageable);

	//@Query(value = "select s from students s where MATCH(s.first_name) AGAINST (:searchParam)", nativeQuery = true)
	//public Page<Student> findBySearchInput(@Param("searchParam") String searchParam, Pageable pageable);
}
