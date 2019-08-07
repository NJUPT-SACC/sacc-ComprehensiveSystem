package com.sacc.comprehensivesystem.modules.messager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Calendar;
import java.util.Date;

/**
 * 消息接收服务
 * 接受来自评测机的实时评测结果
 */
@Component
public class MessageReceiver implements MessageListener{

    Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @JmsListener(destination = "vojJudgeResultQueue")
    @Override
    public void onMessage(Message message) {
        if ( message instanceof MapMessage) {
            final MapMessage mapMessage = (MapMessage) message;

            try {
                String event = mapMessage.getString("event");

                if ( "ErrorOccurred".equals(event) ) {
                    errorHandler(mapMessage);
                } else if ( "CompileFinished".equals(event) ) {
                    compileFinishedHandler(mapMessage);
                } else if ( "TestPointFinished".equals(event) ) {
                    testPointFinishedHandler(mapMessage);
                } else if ( "AllTestPointsFinished".equals(event) ) {
                    allTestPointsFinishedHandler(mapMessage);
                } else if ( "KeepAlive".equals(event) ) {
                    receiveFromAliveJudgersHandler(mapMessage);
                } else {
                    logger.error("Unknown Event Received. Event = {}", event);
                }
            } catch (JMSException e) {
                logger.error("Error: {}\n{}",e.getMessage(), e.getStackTrace());
            }
        }
    }

    /**
     * 处理评测机发生内部错误的消息.
     * @param mapMessage - 消息队列中收到的MapMessage对象
     * @throws JMSException
     */
    private void errorHandler(MapMessage mapMessage) throws JMSException {
        long submissionId = mapMessage.getLong("submissionId");
        eventPublisher.publishEvent(new SubmissionEvent(this, submissionId, "System Error", "System Error.", true));
        logger.debug("Submission #{} returned [System Error].", submissionId);
    }

    /**
     * 处理评测机编译完成时的消息.
     * @param mapMessage - 消息队列中收到的MapMessage对象
     * @throws JMSException
     */
    private void compileFinishedHandler(MapMessage mapMessage) throws JMSException {
        long submissionId = mapMessage.getLong("submissionId");
        boolean isSuccessful = mapMessage.getBoolean("isSuccessful");
        String log = mapMessage.getString("log");

        if ( isSuccessful ) {
            String message = "Compile Successfully.\n\n";
            eventPublisher.publishEvent(new SubmissionEvent(this, submissionId, "Running", message, false));
            logger.info("Submission #{} returned [Compile Successfully].", submissionId);
        } else {
            eventPublisher.publishEvent(new SubmissionEvent(this, submissionId, "Compiler Error", log, true));
            logger.info("Submission #{} returned [Compile Error].\n\tError Message:{}", submissionId, log);
        }
    }

    /**
     * 处理评测机完成单个测试点的消息.
     * @param mapMessage - 消息队列中收到的MapMessage对象
     * @throws JMSException
     */
    private void testPointFinishedHandler(MapMessage mapMessage) throws JMSException {
        long submissionId = mapMessage.getLong("submissionId");
        int checkpointId = mapMessage.getInt("checkpointId");
        String runtimeResult = mapMessage.getString("runtimeResult");
        int usedTime = mapMessage.getInt("usedTime");
        int usedMemory = mapMessage.getInt("usedMemory");
        int score = mapMessage.getInt("score");

        String message = String.format("- Test Point #%d: %s, Time = %d ms, Memory = %d KB, Score = %d\n",
                new Object[] { checkpointId, runtimeResult, usedTime, usedMemory, score });
        eventPublisher.publishEvent(new SubmissionEvent(this, submissionId, "Running", message, false));

        logger.info("Submission #{}/ Checkpoint#{} returned [{}] (Time = {}ms, Memory = {} KB, Score = {}).",
                submissionId, checkpointId, runtimeResult, usedTime, usedMemory, score);
    }

    /**
     * 处理评测机完成全部测试点的消息.
     * @param mapMessage - 消息队列中收到的MapMessage对象
     * @throws JMSException
     */
    private void allTestPointsFinishedHandler(MapMessage mapMessage) throws JMSException {
        long submissionId = mapMessage.getLong("submissionId");
        String runtimeResult = mapMessage.getString("runtimeResult");
        int usedTime = mapMessage.getInt("totalTime");
        int usedMemory = mapMessage.getInt("maxMemory");
        int score = mapMessage.getInt("totalScore");

        String message = String.format("\n%s, Time = %d ms, Memory = %d KB, Score = %d\n",
                new Object[] { runtimeResult, usedTime, usedMemory, score });
        eventPublisher.publishEvent(new SubmissionEvent(this, submissionId, runtimeResult, message, true));

        logger.info(String.format("Submission #%d judge completed and returned [%s] (Time = %d ms, Memory = %d KB, Score = %d).",
                new Object[] { submissionId, runtimeResult, usedTime, usedMemory, score }));
    }

    /**
     * 处理来自评测机的Keep-Alive消息.
     * 用于在Web端获取后端评测机的信息.
     * @param mapMessage - 消息队列中收到的MapMessage对象
     * @throws JMSException
     */
    private void receiveFromAliveJudgersHandler(MapMessage mapMessage) throws JMSException {
        String judgerUsername = mapMessage.getString("username");
        String judgerDescription = mapMessage.getString("description");
        long heartbeatTimeInMillis = mapMessage.getLong("heartbeatTime");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(heartbeatTimeInMillis);
        Date heartbeatTime = calendar.getTime();

        eventPublisher.publishEvent(new KeepAliveEvent(this, judgerUsername, judgerDescription, heartbeatTime));
        logger.info("Received heartbeat from Judger[{}]", judgerUsername);
    }
}
