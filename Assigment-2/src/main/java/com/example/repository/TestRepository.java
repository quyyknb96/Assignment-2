package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer>{
	Test findById(Integer id);
	List<Test> findByName(String name);
	List<Test> findByDetail(String detail);
}
