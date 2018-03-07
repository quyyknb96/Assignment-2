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

import com.example.entity.GradudeExam;
import com.example.service.GradudeExamService;

@RestController
@RequestMapping(value = "/api")
public class GradudeExamController {

	@Autowired
	private GradudeExamService gradudeExamService;

	@RequestMapping(value = "/gradudeExam", method = RequestMethod.GET)
	public ResponseEntity<List<GradudeExam>> listGetAll() {
		List<GradudeExam> list = gradudeExamService.getAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<GradudeExam>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/gradudeExam/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable int id) {
		if (gradudeExamService.getById(id) != null) {
			return new ResponseEntity<GradudeExam>(gradudeExamService.getById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/gradudeExam", method = RequestMethod.POST)
	public ResponseEntity<?> addGradudeExam(GradudeExam gradudeExam, UriComponentsBuilder ucBuilder) {
		int student = gradudeExam.getStudent().getId();
		int test = gradudeExam.getTest().getId();
		gradudeExam = new GradudeExam(gradudeExamService.getByStudentId(student), gradudeExamService.getByTestId(test),
				gradudeExam.getMark(), gradudeExam.getNotice());
		if (gradudeExamService.isExist(gradudeExam)) {
			return new ResponseEntity<>("Student already exist", HttpStatus.CONFLICT);
		} else {
			if (gradudeExamService.add(gradudeExam) > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(
						ucBuilder.path("/api/gradudeExam/{id}").buildAndExpand(gradudeExam.getId()).toUri());
				return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
	}

	@RequestMapping(value = "/gradudeExam/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGradudeExam(@PathVariable int id, GradudeExam gradudeExam) {
		GradudeExam gradudeExam_current = gradudeExamService.getById(id);
		int student = gradudeExam.getStudent().getId();
		int test = gradudeExam.getTest().getId();
		gradudeExam = new GradudeExam(gradudeExamService.getByStudentId(student), gradudeExamService.getByTestId(test),
				gradudeExam.getMark(), gradudeExam.getNotice());
		if (gradudeExam_current == null) {
			return new ResponseEntity<>("Unable to update. GradudeExam not found", HttpStatus.NOT_FOUND);
		} else {
			gradudeExam.setId(gradudeExam_current.getId());
			gradudeExamService.update(gradudeExam);
			return new ResponseEntity<GradudeExam>(gradudeExam, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/gradudeExam/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTest(@PathVariable int id) {
		GradudeExam gradudeExam = gradudeExamService.getById(id);
		if (gradudeExam == null) {
			return new ResponseEntity<>("Unable to delete. GradudeExam not found", HttpStatus.NOT_FOUND);
		} else {
			gradudeExamService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
