package com.sacc.comprehensivesystem.modules.voj.dao;

import com.sacc.comprehensivesystem.modules.voj.entity.Checkpoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckpointDao {
    /**
     * [此方法仅供管理员使用]
     * 获取系统中试题测试点的总数.
     * @return 系统中试题测试点的总数
     */
    long getNumberOfCheckpoints();

    /**
     * 获取某个试题的全部测试点.
     * @param problemId - 试题的唯一标识符
     * @return 某个试题的全部测试点
     */
    List<Checkpoint> getCheckpointsUsingProblemId(@Param("problemId") long problemId);

    /**
     * [此方法仅供管理员使用]
     * 创建测试点.
     * @param checkpoint - 测试点
     */
    int createCheckpoint(Checkpoint checkpoint);

    /**
     * [此方法仅供管理员使用]
     * 删除某个试题的全部测试点.
     * @param problemId - 试题的唯一标识符
     */
    int deleteCheckpoint(long problemId);
}
