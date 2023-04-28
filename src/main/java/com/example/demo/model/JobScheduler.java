package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "job_scheduler")
public class JobScheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_name",nullable = false,unique = true,length = 150)
    private String taskName;

    @Column(name = "description")
    private String description;

    @Column(name = "task_type" , nullable = false , length = 50)
    private Integer taskType;

    @Column(name = "status" , nullable = false , length = 1)
    private Integer status;

    @Column(name = "last_run_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastRunTime;

    @Column(name = "next_run_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextRunTime;

    @Column(name = "trigger_type" , nullable = false , length = 50)
    private Integer triggerType;
}
