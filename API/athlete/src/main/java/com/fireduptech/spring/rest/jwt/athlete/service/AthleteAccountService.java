package com.fireduptech.spring.rest.jwt.athlete.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.fireduptech.spring.rest.jwt.athlete.domain.AthleteAccount;


public interface AthleteAccountService {

    @PreAuthorize( " hasAnyRole( 'ROLE_ATHLETE' ) " )
    AthleteAccount getAthleteAccount( int athleteAccountId );

    // JPA Retrieval
    AthleteAccount findByFirstName( String firstName );

    // JPA Retrieval
    AthleteAccount findById( int id );

}