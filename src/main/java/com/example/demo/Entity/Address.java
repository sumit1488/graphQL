package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

	private String addressLine1;
	
	private String addressLine2;
	
	private int pincode;
	
	private String landMarg;
	
	private String state;
	
	private String country;
}
