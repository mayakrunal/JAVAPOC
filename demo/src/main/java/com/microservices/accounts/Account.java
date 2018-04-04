package com.microservices.accounts;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACCOUNT")
public class Account implements Serializable
{

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	/**
	 * This is a very simple, and non-scalable solution to generating unique
	 * ids. Not recommended for a real application. Consider using the
	 * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
	 *
	 * @return The next available id.
	 */
	protected static Long getNextId()
	{
		synchronized (nextId)
		{
			return nextId++;
		}
	}

	@Id
	protected Long id;

	protected String number;

	@Column(name = "name")
	protected String owner;

	protected BigDecimal balance;

	/**
	 * Default constructor for JPA only.
	 */
	protected Account()
	{
		balance = BigDecimal.ZERO;
	}

	/**
	 * Instantiates a new account.
	 *
	 * @param number
	 *            the number
	 * @param owner
	 *            the owner
	 */
	public Account(String number, String owner)
	{
		id = getNextId();
		this.number = number;
		this.owner = owner;
		balance = BigDecimal.ZERO;
	}

	public void deposit(BigDecimal amount)
	{
		balance.add(amount);
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

	public void withdraw(BigDecimal amount)
	{
		balance.subtract(amount);
	}
}
