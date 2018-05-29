package com.pwc.quartzdemo.controller;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

import com.pwc.quartzdemo.bean.params.JobParams;
import com.pwc.quartzdemo.service.JobScheduleService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController.java
 */

@RestController
public class JobScheduleController {

    @Autowired
    private JobScheduleService jobScheduleService;

    @PostMapping("/schedule")
    public void schedule(@RequestBody final JobParams params) throws ParseException, SchedulerException {
        jobScheduleService.scheduleJob(params);
    }

    @PutMapping("/unScheduleJob/{jobName}")
    public void unScheduleJob(@Valid @PathVariable final String jobName) throws SchedulerException {
        jobScheduleService.unScheduleJob(jobName);
    }

    @GetMapping("/allJobNames")
    public List<String> getAllJobsNames() throws SchedulerException {
        return jobScheduleService.getAllJobsNames();
    }

    @GetMapping("/removeAll")
    public void removeAll() throws SchedulerException {
        jobScheduleService.removeAll();
    }
}
