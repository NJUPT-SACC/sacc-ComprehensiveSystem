package com.sacc.comprehensivesystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Data
@Component
@ConfigurationProperties(prefix = "back")
@MapperScan(basePackages = BackDataBaseConfig.PACKAGE, sqlSessionFactoryRef = "backSqlSessionFactory")
public class BackDataBaseConfig {
    static final String PACKAGE = "com.sacc.comprehensivesystem.modules.voj.dao";

    private static final String MAPPER_LOCATION = "classpath:mapper/com/sacc/comprehensivesystem/modules/voj/*.xml";

    @Value("${spring.back.datasource.url}")
    private String url;
    @Value("${spring.back.datasource.username}")
    private String username;
    @Value("${spring.back.datasource.password}")
    private String password;
    @Value("${spring.back.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.back.datasource.continueOnError}")
    private boolean continueOnError;
    @Value("${spring.back.datasource.filters}")
    private String filters;
    @Value("${spring.back.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.back.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.back.datasource.maxWait}")
    private long maxWait;
    @Value("${spring.back.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.back.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.back.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${spring.back.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.back.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.back.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.back.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.back.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.back.datasource.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;


    @Bean(name = "backDataSource")
    public DataSource backDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        //监控统计拦截的filters
        druid.setFilters(filters);

        //配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);

        //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);

        //最大连接池数量
        druid.setMaxActive(maxActive);

        //获取连接时最大等待时间，单位毫秒
        druid.setMaxWait(maxWait);

        //最小连接池数量
        druid.setMinIdle(minIdle);

        //间隔多久进行一次一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        //一个连接在池中最小生存时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        //检测连接是否为有效的sql
        druid.setValidationQuery(validationQuery);

        //建议设置为true，不影响性能，并能保证安全性
        druid.setTestWhileIdle(testWhileIdle);

        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);

        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);

        //打开PSCache时，指定每个连接上PSCache的大小
        druid.setMaxOpenPreparedStatements(maxOpenPreparedStatements);

        return druid;
    }

    @Bean(name = "backTransactionManager")
    public DataSourceTransactionManager backTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(backDataSource());
    }

    @Bean(name = "backSqlSessionFactory")
    public SqlSessionFactory backSqlSessionFactory(
            @Qualifier("backDataSource")DataSource backDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(backDataSource);
        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));

        return sessionFactoryBean.getObject();
    }
}
