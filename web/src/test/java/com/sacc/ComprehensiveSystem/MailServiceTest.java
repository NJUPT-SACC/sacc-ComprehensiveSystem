package com.sacc.ComprehensiveSystem;

import com.sacc.ComprehensiveSystem.modules.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() throws Exception {
        mailService.sendSimpleMail("1095755891@qq.com", "test邮件", "HelloMEIgeren");
    }
}
