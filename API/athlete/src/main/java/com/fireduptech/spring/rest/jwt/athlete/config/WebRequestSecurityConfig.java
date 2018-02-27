package com.fireduptech.spring.rest.jwt.athlete.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.fireduptech.spring.rest.jwt.athlete.security.MyRestAuthenticationEntryPoint;
import com.fireduptech.spring.rest.jwt.athlete.security.MyAuthenticationSuccessHandler;
import com.fireduptech.spring.rest.jwt.athlete.security.MyAuthenticationFailureHandler;
import com.fireduptech.spring.rest.jwt.athlete.security.MyLogoutSuccessHandler;

import org.springframework.security.config.http.SessionCreationPolicy;

import com.fireduptech.spring.rest.jwt.athlete.security.JwtAuthenticationFilter;
import com.fireduptech.spring.rest.jwt.athlete.security.JwtAuthorisationFilter;


import org.springframework.security.authentication.AuthenticationManager;


@Configuration
@EnableWebSecurity
public class WebRequestSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    MyRestAuthenticationEntryPoint myRestAuthenticationEntryPoint;

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;



    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.csrf().disable().cors()
                .and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().formLogin().successHandler( myAuthenticationSuccessHandler )
                .and().formLogin().failureHandler( myAuthenticationFailureHandler )
                .and().logout().logoutSuccessHandler( myLogoutSuccessHandler )
                .and().exceptionHandling().authenticationEntryPoint( myRestAuthenticationEntryPoint )
                .and()
                .addFilter( new JwtAuthenticationFilter( authenticationManager ) )
                .addFilter( new JwtAuthorisationFilter( authenticationManager ) );


    }   // End of method configure()...




    /*
    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.csrf().disable().cors()
                .and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().formLogin().successHandler( myAuthenticationSuccessHandler )
                .and().formLogin().failureHandler( myAuthenticationFailureHandler )
                .and().logout().logoutSuccessHandler( myLogoutSuccessHandler )
                .and().exceptionHandling().authenticationEntryPoint( myRestAuthenticationEntryPoint );


    }
    */



    /*
        @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().formLogin().successHandler( myAuthenticationSuccessHandler )
                .and().formLogin().failureHandler( myAuthenticationFailureHandler )
                .and().logout().logoutSuccessHandler( myLogoutSuccessHandler )
                .and().exceptionHandling().authenticationEntryPoint( myRestAuthenticationEntryPoint );

    }
     */

}
