package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.GradudeExam;
import com.example.entity.Student;
import com.example.entity.Test;
import com.example.repository.GradudeExamRepository;

@Service
public class GradudeExamService {
	
	@Autowired
	private GradudeExamRepository gradudeExamRepository;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private StudentService studentService;
		
	public int add(GradudeExam gradudeExam) {
		gradudeExamRepository.save(gradudeExam);
		return gradudeExam.getId();
	}
	
	public boolean isExist(GradudeExam gradudeExam) {
		if(gradudeExamRepository.findById(gradudeExam.getId()) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteById(int id) {
		if (gradudeExamRepository.findById(id) != null) {
			gradudeExamRepository.delete(id);
			return true;
		}else {
			return false;
		}
	}
	
	public Test getByTestId(int id) {
		return testService.getById(id);
	}
	
	public Student getByStudentId(int id) {
		return studentService.getById(id);
	}
	
	public List<GradudeExam> getAll(){
		return gradudeExamRepository.findAll();
	}
	
	public GradudeExam getById(int id) {
		return gradudeExamRepository.findById(id);
	}
	
	public List<GradudeExam> getByStudent(Student student) {
		return gradudeExamRepository.findByStudent(student);
	}
	
	public List<GradudeExam> getByTest(Test test){
		return gradudeExamRepository.findByTest(test);
	}
	
	public List<GradudeExam> getByMark(Double mark){
		return gradudeExamRepository.findByMark(mark);
	}	
	
	public boolean update(GradudeExam gradudeExam) {
		if(gradudeExamRepository.save(gradudeExam) != null) {
			return true;
		}else {
			return false;
		}
	}

}
