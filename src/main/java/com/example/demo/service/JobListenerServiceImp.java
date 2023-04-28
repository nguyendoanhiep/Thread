package com.example.demo.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class JobListenerServiceImp implements JobListener {
    @Override
    public String getName() {
        return "Get name";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

    }
}
