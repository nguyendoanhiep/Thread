package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class JobSchedulerDto {
    private Long id;
    private String taskName;
    private String description;
    private Integer taskType;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastRunTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextRunTime;
    private Integer triggerType;
}
