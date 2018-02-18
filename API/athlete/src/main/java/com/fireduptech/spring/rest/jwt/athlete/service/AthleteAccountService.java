package com.fireduptech.spring.rest.jwt.athlete.service;

import com.fireduptech.spring.rest.jwt.athlete.domain.AthleteAccount;


public interface AthleteAccountService {

    AthleteAccount getAthleteAccount( int athleteAccountId );

    // JPA Retrieval
    AthleteAccount findByFirstName( String firstName );

    // JPA Retrieval
    AthleteAccount findById( int id );

}