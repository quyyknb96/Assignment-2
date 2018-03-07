package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public int add(Student student) {
		studentRepository.save(student);
		return student.getId();
	}
	
	public boolean isExist(Student student) {
		if(studentRepository.findById(student.getId()) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteById(int id) {
		if (studentRepository.findById(id) != null) {
			studentRepository.delete(id);
			return true;
		}else {
			return false;
		}
	}
	
	public List<Student> getAll(){
		return studentRepository.findAll();
	}
	
	public Student getById(int id) {
		return studentRepository.findById(id);
	}
	
	public List<Student> getByName(String name){
		return studentRepository.findByName(name);
	}
	
	public List<Student> getByDetail(String address){
		return studentRepository.findByAddress(address);
	}
	
	public boolean update(Student student) {
		if(studentRepository.save(student) != null) {
			return true;
		}else {
			return false;
		}
	}
}
