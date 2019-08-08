package com.sacc.comprehensivesystem;

import com.sacc.comprehensivesystem.modules.messager.ApplicationEventListener;
import com.sacc.comprehensivesystem.modules.messager.SubmissionEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testSseEmitter {

    @Autowired
    private ApplicationEventListener applicationEventListener;

    @Test
    public void test() {
        try {
            SubmissionEvent submissionEvent = new SubmissionEvent(this, 233, "chenggong", "11", true);
            applicationEventListener.submissionEventHandler(submissionEvent);
        } catch (IOException e) {

        }
        return;
    }
}
