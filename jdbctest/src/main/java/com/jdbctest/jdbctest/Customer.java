/*
 *
 */
package com.jdbctest.jdbctest;

public class Customer
{
	private long id;
	private String firstName, lastName;

	public Customer(long id, String firstName, String lastName)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public long getId()
	{
		return this.id;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

}
