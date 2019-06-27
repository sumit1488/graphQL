package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	private String empId;

	private String empName;

	private Address empAddress;

	private boolean isPermanent;

	private int empTokenNumber;

}
