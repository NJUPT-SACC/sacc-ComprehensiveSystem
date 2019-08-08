package com.sacc.comprehensivesystem.modules.competition.service;

import com.sacc.comprehensivesystem.modules.assignment.entity.QuestionBank;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionDao;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    @Autowired
    CompetitionDao competitionDao;

    public List<Competition> listCompetition() {
        List<Competition> result = new ArrayList<>();
        result = competitionDao.listCompetition();
        return result;
    }

    public List<QuestionBank> listQuestion(long id) {
        List<QuestionBank> result = competitionDao.queryQuestion(id);
        return result;
    }
}
