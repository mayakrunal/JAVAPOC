package com.pwc.quartzdemo.job;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Slf4j
@Component
public class SampleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Hello World");
    }
}
