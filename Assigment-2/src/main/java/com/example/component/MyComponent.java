package com.example.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Test;
import com.example.service.TestService;

@Component
public class MyComponent implements CommandLineRunner {

	@Autowired
	private TestService testService;
	
	@Override
	public void run(String... args) throws Exception {
		Test test = new Test("ta ngoc quy", "none");
		//System.out.println(testService.deleteById(1)); 
	}

}
