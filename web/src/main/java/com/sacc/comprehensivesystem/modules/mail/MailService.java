package com.sacc.comprehensivesystem.modules.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Component
public class MailService  {
    private Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMail(String to, String subject, String user) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            Context context = new Context();
            context.setVariable("id", user);
            String mailContext = templateEngine.process("emailTemplate", context);
            helper.setText(mailContext, true);
            mailSender.send(message);
            logger.info("邮件已经发送");
        } catch (Exception e) {
            logger.error("Error: {}\n{}", e.getMessage(), e.getStackTrace());
        }
    }
}
