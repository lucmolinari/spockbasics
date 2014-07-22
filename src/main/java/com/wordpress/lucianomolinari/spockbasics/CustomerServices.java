package com.wordpress.lucianomolinari.spockbasics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wordpress.lucianomolinari.spockbasics.InvalidCustomerException.InvalidCustomerCause;

/**
 * Responsible for managing customers.
 * 
 * @author Luciano Molinari
 */
public class CustomerServices {

	/**
	 * Simple map to keep customers in memory.
	 */
	private static Map<Long, Customer> customers = new LinkedHashMap<Long, Customer>();

	/**
	 * Initializes the map {@link #customers}.
	 */
	public void init() {
		customers = new LinkedHashMap<>();
	}

	/**
	 * Persists a new {@link Customer} in the system.
	 * 
	 * @param customer
	 *            The {@link Customer} to be persisted.
	 * @throws InvalidCustomerException
	 *             If the {@link Customer} is not valid to be persisted.
	 */
	public void add(Customer customer) throws InvalidCustomerException {
		if (customer.getId() == null) {
			throw new InvalidCustomerException(InvalidCustomerCause.ID_NOT_INFORMED);
		}
		if (customer.getName() == null) {
			throw new InvalidCustomerException(InvalidCustomerCause.NAME_NOT_INFORMED);
		}
		if (customers.containsKey(customer.getId())) {
			throw new InvalidCustomerException(InvalidCustomerCause.DUPLICATED_ID);
		}
		customers.put(customer.getId(), customer);
	}

	/**
	 * @return A {@link List} with all the {@link Customer} of the system.
	 */
	public List<Customer> findAll() {
		return Collections.unmodifiableList(new ArrayList<>(customers.values()));
	}

}