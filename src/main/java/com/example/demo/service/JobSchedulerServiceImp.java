package com.example.demo.service;

import com.example.demo.dto.JobSchedulerDto;
import com.example.demo.job.BootJob;
import com.example.demo.model.JobScheduler;
import com.example.demo.repo.JobSchedulerRepository;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobSchedulerServiceImp implements JobSchedulerService {
    @Autowired
    JobSchedulerRepository jobSchedulerRepository;

    @Override
    public JobScheduler saveJobScheduler(JobSchedulerDto dto) {
        JobScheduler entity = new JobScheduler();
        entity.setId(dto.getId());
        entity.setTaskName(dto.getTaskName());
        entity.setNextRunTime(dto.getNextRunTime());
        entity.setDescription(dto.getDescription());
        entity.setLastRunTime(dto.getLastRunTime());
        entity.setStatus(dto.getStatus());
        entity.setTriggerType(dto.getTriggerType());
        entity.setTaskType(dto.getTaskType());
        return jobSchedulerRepository.save(entity);
    }

    @Override
    public Page<JobScheduler> get(String taskName, Integer taskType, Pageable pageable) {
        try {
            if (taskName.equals("")) {
                taskName = null;
            }
            return jobSchedulerRepository.get(taskName, taskType, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String action(Long id, String action) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            String message = null;
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobScheduler jobScheduler = jobSchedulerRepository.findById(id).get();
            if (action.equals("Perform")) {
                if (scheduler.checkExists(JobKey.jobKey(jobScheduler.getTaskName(), "JOB_GROUP"))) {
                    scheduler.resumeJob(JobKey.jobKey(jobScheduler.getTaskName(), "JOB_GROUP"));
                    message = " Resume " + jobScheduler.getTaskName() + " success ";
                } else {
                    JobDetail jobDetail = JobBuilder.newJob().ofType(BootJob.class)
                            .withIdentity(jobScheduler.getTaskName(), "JOB_GROUP")
                            .withDescription(jobScheduler.getDescription())
                            .storeDurably(true)
                            .build();
                    String timeLoop = "0/20 * * * * ?";
                    Date dateLocal = jobScheduler.getNextRunTime();
                    Date dateUtc = new Date(dateLocal.getTime());
                    dateUtc.setHours(dateUtc.getHours() - 7);
                    Trigger jobTrigger = TriggerBuilder.newTrigger()
                            .withIdentity(jobScheduler.getTaskName(), "JOB_GROUP")
                            .startAt(dateUtc)
                            .withSchedule(CronScheduleBuilder.cronSchedule(timeLoop))
                            .build();

                    scheduler.scheduleJob(jobDetail, jobTrigger);
                    scheduler.start();
                    message = " Run " + jobScheduler.getTaskName() + " Success ";
                }
            }
            if (action.equals("Pause")) {
                scheduler.pauseJob(JobKey.jobKey(jobScheduler.getTaskName(), "JOB_GROUP"));
                message = " Pause " + jobScheduler.getTaskName() + " Success ";
            }
            System.out.println(message);
            scheduler.getListenerManager().addJobListener(new JobListenerServiceImp(), KeyMatcher.keyEquals(JobKey.jobKey(jobScheduler.getTaskName(), "JOB_GROUP")));

            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return "Run false";
        }
    }


    @Override
    public Long deleteJobScheduler(Long id) {
        try {
            jobSchedulerRepository.deleteById(id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
