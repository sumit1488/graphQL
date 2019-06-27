package com.example.demo.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AllEmployeesFetcher implements DataFetcher<List<Employee>>{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public List<Employee> get(DataFetchingEnvironment environment) {
		return employeeRepo.findAll();
	}
 
}
