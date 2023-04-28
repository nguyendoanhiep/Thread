package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BootJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        switch (context.getJobDetail().getKey().getName()) {
            case "Job 1":
                job1();
                break;
            case "Job 2":
                job2();
                break;
        }
    }
    private void job1() {
        System.out.println("job 1 is running");
    }

    private void job2() {
        System.out.println("job 2 is running");
    }
}