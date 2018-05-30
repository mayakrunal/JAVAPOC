package com.pwc.quartzdemo.job;

import java.util.HashMap;
import java.util.Map;

import com.pwc.quartzdemo.dao.JobMessageDao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Slf4j
@Component
public class SampleJob extends QuartzJobBean {

    private String jobName;

    private String jobMessage;

    @Autowired
    JobMessageDao jobMessageDao;

    @Override
    protected void executeInternal(final JobExecutionContext jobExecutionContext) {
        log.info("Executing scheduled job");
        jobMessageDao.addJobMessage(jobName, jobMessage);
        log.info("Finished executing Scheduled job");
    }

    public static Map<String, String> getJobDataMap(final String jobName, final String jobMessage) {
        final Map<String, String> dataMap = new HashMap<>();
        dataMap.put("jobName", jobName);
        dataMap.put("jobMessage", jobMessage);
        return dataMap;
    }
}
