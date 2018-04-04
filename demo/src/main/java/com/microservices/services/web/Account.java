package com.microservices.services.web;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Account DTO - used to interact with the {@link WebAccountsService}.
 *
 */
@JsonRootName("Account")
public class Account
{
	protected Long id;
	protected String number;
	protected String owner;
	protected BigDecimal balance;

	/**
	 * Default constructor for JPA only.
	 */
	protected Account()
	{
		balance = BigDecimal.ZERO;
	}

	public BigDecimal getBalance()
	{
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public long getId()
	{
		return id;
	}

	public String getNumber()
	{
		return number;
	}

	public String getOwner()
	{
		return owner;
	}

	protected void setBalance(BigDecimal value)
	{
		balance = value;
		balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * Set JPA id - for testing and JPA only. Not intended for normal use.
	 *
	 * @param id
	 *            The new id.
	 */
	protected void setId(long id)
	{
		this.id = id;
	}

	protected void setNumber(String accountNumber)
	{
		this.number = accountNumber;
	}

	protected void setOwner(String owner)
	{
		this.owner = owner;
	}

	@Override
	public String toString()
	{
		return number + " [" + owner + "]: $" + balance;
	}
}
