package com.pwc.quartzdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}