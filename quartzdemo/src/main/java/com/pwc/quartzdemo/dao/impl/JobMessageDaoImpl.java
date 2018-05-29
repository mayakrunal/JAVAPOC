package com.pwc.quartzdemo.dao.impl;

import com.pwc.quartzdemo.dao.AbstractDao;
import com.pwc.quartzdemo.dao.JobMessageDao;
import org.springframework.stereotype.Repository;

@Repository
public class JobMessageDaoImpl extends AbstractDao implements JobMessageDao {
    @Override
    public void addJobMessage(final String jobName, final String message) {
        final String sql = "INSERT INTO [dbo].[JobMessages]([JobName],[Message],[CreatedDate]) VALUES (?,?,GETDATE())";

        jdbcTemplate.update(sql,jobName,message);
    }
}
