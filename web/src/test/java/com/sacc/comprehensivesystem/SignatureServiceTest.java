package com.sacc.comprehensivesystem;

import com.sacc.comprehensivesystem.admin.sys.dao.SysUserDao;
import com.sacc.comprehensivesystem.admin.sys.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignatureServiceTest {
    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void testSendMail() throws Exception {
        SysUser sysUser = sysUserDao.get(2l);

    }
}
