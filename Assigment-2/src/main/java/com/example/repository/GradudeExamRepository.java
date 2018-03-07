package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.GradudeExam;
import com.example.entity.Student;
import com.example.entity.Test;

@Repository
public interface GradudeExamRepository extends JpaRepository<GradudeExam, Integer>{
	GradudeExam findById(Integer id);
	List<GradudeExam> findByTest(Test test);
	List<GradudeExam> findByStudent(Student student);
	List<GradudeExam> findByMark(Double mark);
}
