package com.fireduptech.spring.rest.jwt.athlete.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.fireduptech.spring.rest.jwt.athlete.security.SecurityConstants.EXPIRATION_TIME;
import static com.fireduptech.spring.rest.jwt.athlete.security.SecurityConstants.HEADER_STRING;
import static com.fireduptech.spring.rest.jwt.athlete.security.SecurityConstants.SECRET;
import static com.fireduptech.spring.rest.jwt.athlete.security.SecurityConstants.TOKEN_PREFIX;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter( AuthenticationManager authenticationManager ) {

        this.authenticationManager = authenticationManager;
    }


    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {

        if ( super.requiresAuthentication( request, response ) ) {

            if ( HttpMethod.OPTIONS.name().equalsIgnoreCase( request.getMethod() ) ) {
                return false;
            }
            Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

            if ( existingAuth == null || !existingAuth.isAuthenticated() ) {
                return true;
            }
        }

        return false;
    }


    @Override
    public Authentication attemptAuthentication( HttpServletRequest req, HttpServletResponse res ) throws AuthenticationException {

        try {
            String username = obtainUsername( req );
            String password = obtainPassword( req );

            //User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password,
                            new ArrayList<>())
            );

        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }


    @Override
    protected void successfulAuthentication( HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth ) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setIssuer( "http://localhost:8080" )
                .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME ) )
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .compact();

        res.addHeader( HEADER_STRING, TOKEN_PREFIX + token );
    }


}
