package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeResource {

	@Autowired
	private GraphQLService graphQLService;

	@PostMapping
	public ResponseEntity<ExecutionResult> getAllEmployees(@RequestBody String query) {
		
		ExecutionResult result = graphQLService.getGraphQL().execute(query);

		return new ResponseEntity<ExecutionResult>(result, HttpStatus.OK);
	}

}
