package com.pwc.quartzdemo.bean.params;

import com.pwc.quartzdemo.bean.enums.TriggerType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobParams {
    private String jobName;

    private String descriptionNote;

    private String jobMessage;

    private TriggerType triggerType;

    private SimpleTriggerParams simpleTriggerParams;

    private CronTriggerParams cronTriggerParams;

}
