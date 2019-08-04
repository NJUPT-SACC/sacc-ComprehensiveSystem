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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Data
@Component
@ConfigurationProperties(prefix = "primary.datasource")
@MapperScan(basePackages = PrimaryDataBaseConfig.PACKAGE, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataBaseConfig {
    static final String PACKAGE = "com.sacc.comprehensivesystem.*";
    /**
     * 加載子目錄所有的mapper
     */
    private static final String MAPPER_LOCATION = "classpath:mapper/com/sacc/comprehensivesystem/**/*.xml";
    private static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";

    @Value("${primary.datasource.url}")
    private String url;
    @Value("${primary.datasource.username}")
    private String username;
    @Value("${primary.datasource.password}")
    private String password;
    @Value("${primary.datasource.driverClassName}")
    private String driverClassName;
    @Value("${primary.datasource.continueOnError}")
    private boolean continueOnError;
    @Value("${primary.datasource.filters}")
    private String filters;
    @Value("${primary.datasource.maxActive}")
    private int maxActive;
    @Value("${primary.datasource.initialSize}")
    private int initialSize;
    @Value("${primary.datasource.maxWait}")
    private long maxWait;
    @Value("${primary.datasource.minIdle}")
    private int minIdle;
    @Value("${primary.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${primary.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${primary.datasource.validationQuery}")
    private String validationQuery;
    @Value("${primary.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${primary.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${primary.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${primary.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${primary.datasource.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;


    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() throws SQLException {
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

    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(
            @Qualifier("primaryDataSource")DataSource primaryDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(primaryDataSource);
        sessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION));
        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));

        return sessionFactoryBean.getObject();
    }

}

