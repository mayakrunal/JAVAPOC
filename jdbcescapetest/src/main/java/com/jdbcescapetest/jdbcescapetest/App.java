/*
 *
 */
package com.jdbcescapetest.jdbcescapetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception
	{
		StringBuilder builder = new StringBuilder();

		String result = jdbcTemplate.queryForObject(builder.toString(), String.class);

		System.out.println(result);
	}
}
