package com.sacc.comprehensivesystem.modules.messager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageSender {

    Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Map<String, Object> mapMessage) {
        Long submissionId = (Long) mapMessage.get("submissionId");

        jmsTemplate.convertAndSend(mapMessage);
        logger.info("Submission task {} has been created.", submissionId);
    }


}
