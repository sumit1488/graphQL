package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Address;
import com.example.demo.Entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.datafetcher.AllEmployeesFetcher;
import com.example.demo.service.datafetcher.EmployeesFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:employee.graphql")
	private Resource resource;

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	private GraphQL graphQL;

	@Autowired
	private AllEmployeesFetcher allEmployeesFetcher;

	@Autowired
	private EmployeesFetcher employeeFetcher;

	@Autowired
	private EmployeeRepository empRepo;

	@PostConstruct
	public void loadDB() throws IOException {

		// Step 1: load data to mongoDB
		loadDataToMongo();

		// Step 2: fetch schema from file
		File schemaFile = resource.getFile();

		// Step 3: Parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private void loadDataToMongo() {
		empRepo.deleteAll();
		Stream.of(
				Employee.builder().empId("E087695").empName("Sumit Pareek").empTokenNumber(4271).isPermanent(false)
						.empAddress(Address.builder().addressLine1("line1").addressLine2("line2").country("India")
								.pincode(124507).landMarg("HDFC Bank").state("Delhi").build())
						.build(),
				Employee.builder().empId("E087600").empName("ABC XYZ").empTokenNumber(7629).isPermanent(true)
						.empAddress(Address.builder().addressLine1("line1").addressLine2("line2").country("India")
								.pincode(124507).landMarg("HDFC Bank").state("Delhi").build())
						.build())
				.forEach(emp -> empRepo.save(emp));

	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring
				.newRuntimeWiring().type("Query", typeWiring -> typeWiring
						.dataFetcher("allEmployees", allEmployeesFetcher).dataFetcher("employee", employeeFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
