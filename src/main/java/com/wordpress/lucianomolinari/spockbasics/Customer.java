package com.wordpress.lucianomolinari.spockbasics;

/**
 * Simple entity that represents a customer.
 * 
 * @author Luciano Molinari
 */
public class Customer {

	/**
	 * Identifies uniquely a {@link Customer}.
	 */
	private Long id;

	/**
	 * Name of the {@link Customer}.
	 */
	private String name;

	/**
	 * Creates a new {@link Customer} with the given id and name.
	 * 
	 * @param id
	 *            The identification of the customer.
	 * @param name
	 *            The name of the customer.
	 */
	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

}