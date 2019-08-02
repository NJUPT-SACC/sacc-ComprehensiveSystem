package com.sacc.ComprehensiveSystem.modules.assignment.dao;

import com.sacc.ComprehensiveSystem.common.dao.BasicDao;
import com.sacc.ComprehensiveSystem.modules.assignment.entity.QuestionBank;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author goufaan
 */
@Mapper
public interface QuestionBankMapper extends BasicDao<QuestionBank> {
}
