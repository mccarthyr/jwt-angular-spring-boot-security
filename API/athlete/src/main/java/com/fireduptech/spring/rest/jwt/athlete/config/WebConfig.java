package com.fireduptech.spring.rest.jwt.athlete.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;

import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

//@EnableWebMvc
@Configuration
//public abstract class WebConfig implements WebMvcConfigurer {
public class WebConfig extends WebMvcConfigurerAdapter {
//public class WebConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping( "/**" )
                .allowedOrigins( "http://localhost:4200" )
                .allowedMethods( "GET", "POST", "DELETE" )
                .allowedHeaders( "*" )
                .allowCredentials( true )
                .exposedHeaders( "Authorization" )
                .maxAge( 3600 );
    }


    /*
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                .addMapping( "/**" )
                .allowedOrigins( "http://localhost:4200" )
                .allowedMethods( "GET", "POST", "DELETE" )
                .allowedHeaders( "*" )
                .allowCredentials( true )
                .maxAge( 3600 );
    }
*/



}







