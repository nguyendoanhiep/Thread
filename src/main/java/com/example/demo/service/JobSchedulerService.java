package com.example.demo.service;

import com.example.demo.dto.JobSchedulerDto;
import com.example.demo.model.JobScheduler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobSchedulerService {
    JobScheduler saveJobScheduler(JobSchedulerDto dto);
    Page<JobScheduler> get(String taskName , Integer taskType , Pageable pageable);

    String action(Long id ,  String action);
    Long deleteJobScheduler(Long id);

}
