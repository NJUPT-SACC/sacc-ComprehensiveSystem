package com.sacc.comprehensivesystem.modules.competition.service;

import com.sacc.comprehensivesystem.admin.Utils.CacheUtils;
import com.sacc.comprehensivesystem.admin.service.LoginService;
import com.sacc.comprehensivesystem.admin.shrio.entity.UserSimpleAuthorizationInfo;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionDao;
import com.sacc.comprehensivesystem.modules.competition.dao.CompetitionQuestionDao;
import com.sacc.comprehensivesystem.modules.competition.entity.Competition;
import com.sacc.comprehensivesystem.modules.competition.entity.CompetitionQuestion;
import com.sacc.comprehensivesystem.modules.competition.entity.RankingItem;
import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


@Service
@Transactional
public class CompetitionService {

    @Autowired
    CompetitionDao competitionDao;
    @Autowired
    CompetitionQuestionDao competitionQuestionDao;

    private volatile static ConcurrentSkipListSet<RankingItem> ranking = null;

    static Logger logger = LoggerFactory.getLogger(LoginService.class);

    public List<RankingItem> queryRank(Long id) {
        List<RankingItem> list = competitionDao.queryRanking(id);
        return list;
    }

}
