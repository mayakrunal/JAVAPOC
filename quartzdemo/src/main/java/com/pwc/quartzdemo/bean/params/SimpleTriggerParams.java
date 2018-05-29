package com.pwc.quartzdemo.bean.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleTriggerParams {

    private long repeatIntervalInSeconds;

    private int repeatCount; //-1 for indefinite time
}
