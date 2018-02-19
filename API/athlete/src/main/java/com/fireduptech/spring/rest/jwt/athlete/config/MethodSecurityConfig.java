package com.fireduptech.spring.rest.jwt.athlete.config;

import javax.sql.DataSource;

import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionCacheOptimizer;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import org.springframework.security.acls.jdbc.JdbcMutableAclService;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;


@Configuration
@EnableGlobalMethodSecurity( prePostEnabled = true, securedEnabled = true )
@PropertySource( "classpath:/application.properties" )
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private String driverClass;

    private String url;

    private String username;

    private String password;


    @Primary
    @Bean( name = "dataSource")
    @ConfigurationProperties( prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler();

    }

    @Override
    protected void configure( final AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception {
        authenticationManagerBuilder.authenticationProvider( authenticationProvider() );
    }


    @Bean
    public JdbcDaoImpl jdbcDao() {

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource( dataSource() );

        return jdbcDao;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService( jdbcDao() );
        authenticationProvider.setPasswordEncoder( encoder() );

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder( 11 );
    }


    @Bean
    public EhCacheBasedAclCache aclCache() {
        return new EhCacheBasedAclCache(aclEhCacheFactoryBean().getObject(), permissionGrantingStrategy(), aclAuthorizationStrategy());
    }

    @Bean
    public EhCacheFactoryBean aclEhCacheFactoryBean() {

        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }

    @Bean
    public EhCacheManagerFactoryBean aclCacheManager() {
        return new EhCacheManagerFactoryBean();
    }


    @Bean
    public LookupStrategy lookupStrategy() {
        return new BasicLookupStrategy( dataSource(), aclCache(), aclAuthorizationStrategy(), permissionGrantingStrategy() );
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }


    @Bean( name = "defaultMethodSecurityExpressionHandler")
    public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {

        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService());

        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        expressionHandler.setPermissionCacheOptimizer( new AclPermissionCacheOptimizer( aclService() ) );

        return expressionHandler;
    }

    @Bean( name = "myAclService")
    public JdbcMutableAclService aclService() {

        JdbcMutableAclService jdbcMutableAclService =  new JdbcMutableAclService( dataSource(), lookupStrategy(), aclCache() );
        jdbcMutableAclService.setClassIdentityQuery( "SELECT @@IDENTITY" );
        jdbcMutableAclService.setSidIdentityQuery( "SELECT @@IDENTITY" );

        return jdbcMutableAclService;
    }



}

