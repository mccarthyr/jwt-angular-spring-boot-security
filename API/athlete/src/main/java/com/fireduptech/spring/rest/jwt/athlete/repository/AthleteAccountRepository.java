package com.fireduptech.spring.rest.jwt.athlete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fireduptech.spring.rest.jwt.athlete.domain.AthleteAccount;


public interface AthleteAccountRepository extends JpaRepository<AthleteAccount, Integer> {

    AthleteAccount findByFirstName( String firstName );

    AthleteAccount findById( int id );

}