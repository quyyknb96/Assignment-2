package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Test;
import com.example.repository.TestRepository;

@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public int add(Test test) {
		testRepository.save(test);
		return test.getId();
	}
	
	public boolean isExist(Test test) {
		if(testRepository.findById(test.getId()) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteById(int id) {
		if (testRepository.findById(id) != null) {
			testRepository.delete(id);
			return true;
		}else {
			return false;
		}
	}
	
	public List<Test> getAll(){
		return testRepository.findAll();
	}
	
	public Test getById(int id) {
		return testRepository.findById(id);
	}
	
	public List<Test> getByName(String name){
		return testRepository.findByName(name);
	}
	
	public List<Test> getByDetail(String detail){
		return testRepository.findByDetail(detail);
	}
	
	public boolean update(Test test) {
		if(testRepository.save(test) != null) {
			return true;
		}else {
			return false;
		}
	}
}
