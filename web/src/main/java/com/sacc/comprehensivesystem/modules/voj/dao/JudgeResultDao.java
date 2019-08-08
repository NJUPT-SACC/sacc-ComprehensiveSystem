package com.sacc.comprehensivesystem.modules.voj.dao;

import com.sacc.comprehensivesystem.modules.voj.entity.JudgeResult;
import org.apache.ibatis.annotations.Param;

public interface JudgeResultDao {
    /**
     * 通过评测结果的唯一标识符获取评测结果对象.
     * @param judgeResultId - 评测结果的唯一标识符
     * @return 预期的评测结果对象或空引用
     */
    JudgeResult getJudgeResultUsingId(@Param("judgeResultId") int judgeResultId);

    /**
     * 通过评测结果的别名获取评测结果对象.
     * @param judgeResultSlug - 评测结果的别名
     * @return 预期的评测结果对象或空引用
     */
    JudgeResult getJudgeResultUsingSlug(@Param("judgeResultSlug") String judgeResultSlug);
}
