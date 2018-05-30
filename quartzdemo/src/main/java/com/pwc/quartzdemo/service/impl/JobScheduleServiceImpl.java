package com.pwc.quartzdemo.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pwc.quartzdemo.bean.params.CronTriggerParams;
import com.pwc.quartzdemo.bean.params.JobParams;
import com.pwc.quartzdemo.bean.params.SimpleTriggerParams;
import com.pwc.quartzdemo.job.SampleJob;
import com.pwc.quartzdemo.service.JobScheduleService;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class JobScheduleServiceImpl implements JobScheduleService {

    //Autowire factory bean Auto configured by Spring boot based on yaml properties
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void scheduleJob(final JobParams params) throws ParseException, SchedulerException {

        final Scheduler scheduler = schedulerFactoryBean.getScheduler();

        final String jobName = params.getJobName();
        //description for job in Quartz job store
        final String jobDescription = params.getDescriptionNote();
        final String jobMessage = params.getJobMessage();
        //set the trigger name
        final String triggerName = "Trigger - " + jobName;

        //build the jobData
        final Map<String, ?> jobData = SampleJob.getJobDataMap(jobName, jobMessage);

        //create the job detail
        final JobDetail jobDetail = createJobDetail(SampleJob.class, jobName, jobDescription, jobData);

        //create the trigger
        Trigger trigger = null;

        switch (params.getTriggerType()) {
        case SIMPLE:
            trigger = createSimpleTrigger(triggerName, params.getSimpleTriggerParams());
            break;
        case CRON:
            trigger = createCronTrigger(triggerName, params.getCronTriggerParams());
            break;
        }
        //schedule the job
        scheduler.scheduleJob(jobDetail, trigger);

    }

    @Override
    public void unScheduleJob(final String jobName) throws SchedulerException {
        final Scheduler scheduler = schedulerFactoryBean.getScheduler();
        final JobKey jobKey = new JobKey(jobName);
        final TriggerKey triggerKey = new TriggerKey("Trigger - " + jobName);
        //unscheduled the job
        scheduler.unscheduleJob(triggerKey);
        //delete the job
        scheduler.deleteJob(jobKey);
    }

    @Override
    public List<String> getAllJobsNames() throws SchedulerException {
        final Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //get all job names
        return scheduler.getJobKeys(GroupMatcher.anyGroup()).stream().map(JobKey::getName).collect(Collectors.toList());
    }

    @Override
    public void removeAll() throws SchedulerException {
        //clear all jobs
        schedulerFactoryBean.getScheduler().clear();

    }

    private static JobDetail createJobDetail(final Class<? extends Job> jobClass, final String jobName, final String descriptionNote,
                                             final Map<String, ?> jobData) {
        final JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        //set job name
        factoryBean.setBeanName(jobName);
        //set job description
        factoryBean.setDescription(descriptionNote);
        //set the Quartz job class
        factoryBean.setJobClass(jobClass);
        //set the job Data
        if (!CollectionUtils.isEmpty(jobData)) {
            factoryBean.setJobDataAsMap(jobData);
        }
        // job has to be durable to be stored in DB:
        factoryBean.setDurability(true);
        //need to call this once all properties are set
        factoryBean.afterPropertiesSet();
        //return the object
        return factoryBean.getObject();
    }

    private static SimpleTrigger createSimpleTrigger(final String triggerName, final SimpleTriggerParams triggerParams) {
        final SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        //set the trigger name
        factoryBean.setBeanName(triggerName);
        //start delay will override the specific start time so make sure set to 0 if start at specific time
        factoryBean.setStartDelay(0L);
        //set the repeat interval
        factoryBean.setRepeatInterval(triggerParams.getRepeatIntervalInSeconds() * 1000);
        // job repeat count , -1 for indefinite time see
        factoryBean.setRepeatCount(triggerParams.getRepeatCount());
        //need to call this once all properties are set
        factoryBean.afterPropertiesSet();
        //return the object
        return factoryBean.getObject();
    }

    private static CronTrigger createCronTrigger(final String triggerName, final CronTriggerParams triggerParams) throws ParseException {
        final CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        //set the trigger name
        factoryBean.setBeanName(triggerName);
        //set start delay
        factoryBean.setStartDelay(0L);
        //set the cron expression
        factoryBean.setCronExpression(triggerParams.getCronExpression());
        //need to call this once all properties are set
        factoryBean.afterPropertiesSet();
        //return the Object
        return factoryBean.getObject();
    }

}
