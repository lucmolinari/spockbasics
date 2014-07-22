package com.wordpress.lucianomolinari.spockbasics;

/**
 * Excpetion that must be thrown when a {@link Customer} to be persisted is not
 * valid.
 * 
 * @author Luciano Molinari
 */
public class InvalidCustomerException extends Exception {

	private static final long serialVersionUID = -6532930714866940079L;

	/**
	 * Declares the possible reasons of why a {@link Customer} is not valid.
	 */
	public enum InvalidCustomerCause {
		ID_NOT_INFORMED, NAME_NOT_INFORMED, DUPLICATED_ID
	}

	private InvalidCustomerCause invalidCustomerCause;

	/**
	 * Creates a new {@link InvalidCustomerException} with the given cause.
	 * 
	 * @param invalidCustomerCause
	 *            The reason why the {@link Customer} is invalid.
	 */
	public InvalidCustomerException(InvalidCustomerCause invalidCustomerCause) {
		this.invalidCustomerCause = invalidCustomerCause;
	}

	public InvalidCustomerCause getInvalidCustomerCause() {
		return invalidCustomerCause;
	}

}