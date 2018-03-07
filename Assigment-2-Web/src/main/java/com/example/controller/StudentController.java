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

import com.example.entity.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> listGetAll() {
		List<Student> list = studentService.getAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable int id) {
		if (studentService.getById(id) != null) {
			return new ResponseEntity<Student>(studentService.getById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Student not found",HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ResponseEntity<?> addStudent(Student student, UriComponentsBuilder ucBuilder) {		
		if (studentService.isExist(student)) {
			return new ResponseEntity<>("Student already exist", HttpStatus.CONFLICT);
		} else {
			if (studentService.add(student) > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getId()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
	}
	
	@RequestMapping(value = "/student/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateTest(@PathVariable int id, Student student){
		Student student_current = studentService.getById(id);
		if(student_current == null) {
			return new ResponseEntity<>("Unable to update. Student not found",HttpStatus.NOT_FOUND);
		}else {
			student.setId(student_current.getId());
			studentService.update(student);
			return new ResponseEntity<Student>(student,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTest(@PathVariable int id){
		Student student = studentService.getById(id);
		if(student == null) {
			return new ResponseEntity<>("Unable to delete. Student not found",HttpStatus.NOT_FOUND);
		}else {
			studentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
