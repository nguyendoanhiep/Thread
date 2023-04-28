package com.example.demo.controller;


import com.example.demo.dto.JobSchedulerDto;
import com.example.demo.model.Cert;
import com.example.demo.model.JobScheduler;
import com.example.demo.repo.CertRepository;
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
@RequestMapping("/cert")
public class CertController {

    @Autowired
    CertRepository certRepository;

    @GetMapping("/get")
    public ResponseEntity<List<Cert>> getALl() {
        List<Cert> ls = certRepository.findAll();
        return new ResponseEntity(ls, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Cert> save(@RequestBody Cert cert) {
        return new ResponseEntity(certRepository.save(cert), HttpStatus.OK);
    }
}
