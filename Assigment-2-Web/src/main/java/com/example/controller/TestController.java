package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.entity.Test;
import com.example.service.TestService;

@RestController
@RequestMapping(value = "/api")
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<List<Test>> listGetAll() {
		List<Test> list = testService.getAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Test>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable int id) {
		if (testService.getById(id) != null) {
			return new ResponseEntity<Test>(testService.getById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Test not found",HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResponseEntity<?> addTest(Test test, UriComponentsBuilder ucBuilder) {		
		if (testService.isExist(test)) {
			return new ResponseEntity<>("Test already exist", HttpStatus.CONFLICT);
		} else {
			if (testService.add(test) > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/test/{id}").buildAndExpand(test.getId()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
	}
	
	@RequestMapping(value = "/test/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateTest(@PathVariable int id, Test test){
		Test test_current = testService.getById(id);
		if(test_current == null) {
			return new ResponseEntity<>("Unable to update. Test not found",HttpStatus.NOT_FOUND);
		}else {
			test.setId(test_current.getId());
			testService.update(test);
			return new ResponseEntity<Test>(test,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/test/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTest(@PathVariable int id){
		Test test = testService.getById(id);
		if(test == null) {
			return new ResponseEntity<>("Unable to delete. Test not found",HttpStatus.NOT_FOUND);
		}else {
			testService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
