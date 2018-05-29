package com.pwc.quartzdemo.service;

import java.text.ParseException;
import java.util.List;

import com.pwc.quartzdemo.bean.params.JobParams;
import org.quartz.SchedulerException;

public interface JobScheduleService {
    void scheduleJob(JobParams params) throws ParseException, SchedulerException;

    void unScheduleJob(String jobName) throws SchedulerException;

    List<String> getAllJobsNames() throws SchedulerException;

    void removeAll() throws SchedulerException;
}
