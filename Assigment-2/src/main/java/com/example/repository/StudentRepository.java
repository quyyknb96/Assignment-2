package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findById(Integer id);
	List<Student> findByName(String name);
	List<Student> findByAddress(String address);
}
