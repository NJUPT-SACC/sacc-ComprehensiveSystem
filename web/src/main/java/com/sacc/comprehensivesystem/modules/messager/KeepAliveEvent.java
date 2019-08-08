package com.sacc.comprehensivesystem.modules.messager;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class KeepAliveEvent extends ApplicationEvent {
    /**
     * 评测机的用户名.
     */
    private final String judgerUsername;

    /**
     * 评测机的描述信息.
     */
    private final String judgerDescription;

    /**
     * 评测机心跳的时间.
     */
    private final Date heartbeatTime;

    /**
     * 唯一的序列化标识符.
     */
    private static final long serialVersionUID = -9218788192064705664L;

    public KeepAliveEvent(Object source, String judgerUsername, String judgerDescription,
                          Date heartBeatTime) {
        super(source);
        this.judgerUsername = judgerUsername;
        this.judgerDescription = judgerDescription;
        this.heartbeatTime = heartBeatTime;
    }

    /**
     * 获取评测机的用户名.
     * @return 评测机的用户名
     */
    public String getJudgerUsername() {
        return judgerUsername;
    }

    /**
     * 获取评测机的描述信息.
     * @return 评测机的描述信息
     */
    public String getJudgerDescription() {
        return judgerDescription;
    }

    /**
     * 获取评测机发送心跳的时间.
     * @return 评测机发送心跳的时间
     */
    public Date getHeartbeatTime() {
        return heartbeatTime;
    }
}
