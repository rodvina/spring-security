package com.roxterra.spring.security;

/**
 * Implement this as an enum to contain specific request header key names
 * @author kahrxo
 *
 */
public interface RequestHeaderAttribute {

	/**
	 * Implement to return the values of the Enum.  This can be done
	 * by simply calling the static method <code>values()</code>
	 * @return
	 */
	public RequestHeaderAttribute[] getValues();
}
