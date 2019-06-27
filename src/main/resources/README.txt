Sample Request :
{
	allEmployees{
	empId
	empName
	}
	
	employee(empId: "E087695"){
	empId
	empName
	empAddress{
	addressLine1
	addressLine2
	pincode
	landMarg
	state
	country
	}
	isPermanent
	empTokenNumber
	}
}