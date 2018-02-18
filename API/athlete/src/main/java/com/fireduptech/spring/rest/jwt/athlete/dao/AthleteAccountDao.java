package com.fireduptech.spring.rest.jwt.athlete.dao;

import com.fireduptech.spring.rest.jwt.athlete.domain.AthleteAccount;

public interface AthleteAccountDao {

    AthleteAccount getAthleteAccount( int athleteAccountId );
}