package com.example.demo.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class EmployeesFetcher implements DataFetcher<Employee> {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public Employee get(DataFetchingEnvironment environment) {
		String empID = environment.getArgument("empId");
		return employeeRepo.findOne(empID);
	}

}
