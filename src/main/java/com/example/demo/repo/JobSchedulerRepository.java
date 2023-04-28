package com.example.demo.repo;

import com.example.demo.model.JobScheduler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobSchedulerRepository extends JpaRepository<JobScheduler,Long> {

    @Query("select js from JobScheduler js where (:taskName is null or js.taskName like %:taskName% ) and ( :taskType = 0 or js.taskType =:taskType)")
    Page<JobScheduler> get(String taskName , Integer taskType ,  Pageable pageable);

//    @Query(value = "select * from mkg.job_scheduler js where (:taskName is null or js.task_name =:taskName ) and ( :taskType is null or js.task_type = :taskType) " ,nativeQuery = true)
//    Page<JobScheduler> get(String taskName , String taskType ,  Pageable pageable);

}
