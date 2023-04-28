package com.example.demo.service;

import com.example.demo.model.Cert;
import com.example.demo.repo.CertRepository;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.List;
import java.util.stream.Collectors;


@Service
@EnableAutoConfiguration
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    CertRepository certRepository;

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) throws MessagingException {
        List<Cert> ls = certRepository.findAll();
        StringBuilder data = new StringBuilder();
        data.append("<html><body><table style=\"width:50%\">");
        data.append("<tr>" +
                "<th>Id</th>" +
                "<th>Name</th>" +
                "</tr>");
        for (Cert entity : ls) {
            data.append("<tr><td>").append(entity.getId()).append("</td>");
            data.append("<td>").append(entity.getName()).append("</td></tr>");
        }
        data.append(" </table></body></html>");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setContent(data , "text/html; charset=utf-8");

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("hiepnd-hn@mk.com.vn");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mailSender.send(mimeMessage);
        System.out.println("Mail Send...");
    }


}
