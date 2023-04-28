package com.example.demo.controller;

import com.example.demo.dto.JobSchedulerDto;
import com.example.demo.model.JobScheduler;
import com.example.demo.service.JobSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/schedule")
public class JobSchedulerController {
    @Autowired
    JobSchedulerService jobSchedulerService;

    @GetMapping("/get")
    public ResponseEntity<Page<JobScheduler>> getALl(@RequestParam String taskName ,
                                                          @RequestParam(required = false) Integer taskType ,
                                                          @RequestParam(defaultValue = "1") int page ,
                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable  = PageRequest.of(page -1 , size );
        Page<JobScheduler> ls = jobSchedulerService.get(taskName,taskType,pageable);
        return new ResponseEntity(ls, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<JobScheduler> save(@RequestBody JobSchedulerDto dto) {
        return new ResponseEntity(jobSchedulerService.saveJobScheduler(dto), HttpStatus.OK);
    }

    @PostMapping("/action")
    public ResponseEntity<String> action(@RequestParam Long id , @RequestParam  String action) {
        return new ResponseEntity(jobSchedulerService.action( id , action), HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<Long> delete(@RequestParam Long id) {
        return new ResponseEntity(jobSchedulerService.deleteJobScheduler(id), HttpStatus.OK);
    }

}
