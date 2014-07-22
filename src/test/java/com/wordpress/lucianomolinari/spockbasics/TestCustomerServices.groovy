package com.wordpress.lucianomolinari.spockbasics

import com.wordpress.lucianomolinari.spockbasics.InvalidCustomerException.InvalidCustomerCause;

import spock.lang.Specification;

class TestCustomerServices extends Specification {

	private CustomerServices customerServices
	
	def setup() {
		customerServices = new CustomerServices()
		customerServices.init()
	}
	
	def "adds a new customer and finds him"() {
		given: "There is no customer in the system"
		customerServices.findAll().size() == 0
		
		and: "A customer called 'Joe' and identified by id 1"
		Customer joe = new Customer(1, "Joe")
		
		when: "Joe is inserted in the system"
		customerServices.add(joe)
		
		then: "Only one customer should exist in the system"
		List<Customer> customers = customerServices.findAll()
		customers.size() == 1
		
		and: "This customer should be Joe"
		customers.get(0).id == 1
		customers.get(0).name == "Joe"
	}
	
	def "adds a new customer with invalid data"(Long id, String name, InvalidCustomerCause expectedCause) {
		setup: "There is already a customer called John with id 1 in the system"
		customerServices.add(new Customer(1, "John"))
		
		when: "There is a request to insert a customer with the given parameters"
		customerServices.add(new Customer(id, name))
		
		then: "An error of type InvalidCustomerException should be thrown"
		InvalidCustomerException error = thrown(InvalidCustomerException.class)
		error.invalidCustomerCause == expectedCause
		
		where:
		id		| name			| expectedCause
		null	| "John"		| InvalidCustomerCause.ID_NOT_INFORMED
		1		| null			| InvalidCustomerCause.NAME_NOT_INFORMED
		1		| "Carl"		| InvalidCustomerCause.DUPLICATED_ID
	}
	
	def "adds a new customer with invalid data - version 2"(Long id, String name, InvalidCustomerCause expectedCause) {
		setup: "There is already a customer called John with id 1 in the system"
		customerServices.add(new Customer(1, "John"))
		
		when: "There is a request to insert a customer with the given parameters"
		customerServices.add(new Customer(id, name))
		
		then: "An error of type InvalidCustomerException should be thrown"
		InvalidCustomerException error = thrown(InvalidCustomerException.class)
		error.invalidCustomerCause == expectedCause
		
		where:
		id << [null, 1, 1]
		name << ["John", null, "Carl"]
		expectedCause << [InvalidCustomerCause.ID_NOT_INFORMED, InvalidCustomerCause.NAME_NOT_INFORMED, 
			InvalidCustomerCause.DUPLICATED_ID]
	}
	
}