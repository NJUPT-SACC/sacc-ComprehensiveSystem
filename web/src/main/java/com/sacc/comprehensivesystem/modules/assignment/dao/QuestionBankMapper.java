package com.sacc.comprehensivesystem.modules.assignment.dao;

import com.sacc.comprehensivesystem.common.dao.BasicDao;
import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author goufaan
 */
@Mapper
public interface QuestionBankMapper extends BasicDao<QuestionBank> {
}
