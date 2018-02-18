package com.fireduptech.spring.rest.jwt.athlete.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import com.fireduptech.spring.rest.jwt.athlete.service.AthleteAccountService;
import com.fireduptech.spring.rest.jwt.athlete.domain.AthleteAccount;



/**
 *  - DEVELOPMENT NOTES -
 * Example use from command line - when on development database
 *
 * curl -GET 'localhost:8080/athleteAccount?acAction=view&athleteAccountId=35'
 * curl -GET 'localhost:8080/athleteAccount?acAction=viewByJpa&athleteFirstName=RichardXXX'
 * curl -GET 'localhost:8080/athleteAccount?acAction=viewByIdJpa&athleteAccountIdJpa=35'
 */
/*
sudo curl -i -X POST -d username=athlete1 -d password=test  -c /opt/cookies.txt http://localhost:8080/springRestApi/login

sudo curl -i -X GET -b /opt/cookies.txt http://localhost:8080/springRestApi/api/athleteAccount/list

sudo curl -i -X GET -b /opt/cookies.txt http://localhost:8080/springRestApi/api/logout

sudo curl -i -X GET -b /opt/cookies.txt 'http://localhost:8080/springRestApi/api/athleteAccount?acAction=view&athleteAccountId=34'

 */

// db154122-93e7-4812-a115-0fe3af19ce47
/*
THESE WORK
sudo curl -i -X POST -d username=user -d password=db154122-93e7-4812-a115-0fe3af19ce47  -c /opt/cookies.txt http://localhost:8080/login

sudo curl -i -X GET -b /opt/cookies.txt 'localhost:8080/athleteAccount?acAction=viewByIdJpa&athleteAccountIdJpa=35'

sudo curl -i -X GET -b /opt/cookies.txt 'localhost:8080/logout'

sudo curl -i -X POST -d username=athlete1 -d password=test  -c /opt/cookies.txt http://localhost:8080/login
*/



@Controller
@RequestMapping( value = "/athleteAccount" )
public class AthleteAccountController {

    @Autowired
    private AthleteAccountService athleteAccountService;


    @RequestMapping( value = "/richard", method = RequestMethod.GET )
    public ResponseEntity<String> getName() {
        return new ResponseEntity<String>( "richard", HttpStatus.OK );
    }




    @RequestMapping( params = "acAction=view", method = RequestMethod.GET )
    public ResponseEntity<AthleteAccount> viewAthleteAccount( @RequestParam( value = "athleteAccountId") int aaId ) {

        return new ResponseEntity<AthleteAccount>( athleteAccountService.getAthleteAccount( aaId ), HttpStatus.OK );
    }



    // JPA Retrieval
    @RequestMapping( params = "acAction=viewByJpa", method = RequestMethod.GET )
    public ResponseEntity<AthleteAccount> viewAthleteAccountByJpa( @RequestParam( value = "athleteFirstName") String firstName ) {

        return new ResponseEntity<AthleteAccount>( athleteAccountService.findByFirstName( firstName ), HttpStatus.OK );
    }


    // JPA Retrieval
    @RequestMapping( params = "acAction=viewByIdJpa", method = RequestMethod.GET )
    public ResponseEntity<AthleteAccount> viewAthleteAccountByIdJpa( @RequestParam( value = "athleteAccountIdJpa") int aaIdJpa ) {

        return new ResponseEntity<AthleteAccount>( athleteAccountService.findById( aaIdJpa ), HttpStatus.OK );
    }


}
