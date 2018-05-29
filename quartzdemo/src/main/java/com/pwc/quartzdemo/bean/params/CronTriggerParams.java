package com.pwc.quartzdemo.bean.params;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CronTriggerParams {
    @NotNull
    private String second;
    @NotNull
    private String minutes;
    @NotNull
    private String hours;
    @NotNull
    private String dayOfMonth;
    @NotNull
    private String month;
    @NotNull
    private String dayOfWeek;

    private String year;

    @JsonIgnore
    public String getCronExpression() {
        final String year = this.year == null ? "" : " " + this.year.trim();

        return second.trim() + " " + minutes.trim() + " " + hours.trim() + " " + dayOfMonth.trim() + " " + month.trim() + " " + dayOfWeek.trim()
                + year;
    }

}
