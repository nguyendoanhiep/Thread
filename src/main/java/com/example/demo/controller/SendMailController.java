package com.example.demo.controller;


import com.example.demo.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/send-email")
public class SendMailController {
    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("/send")
    public String sendMail(@RequestParam String toEmail,
                           @RequestParam String body,
                           @RequestParam String subject) throws MessagingException {
        emailSenderService.sendSimpleEmail(toEmail,body,subject);
        return "";
    }
}
