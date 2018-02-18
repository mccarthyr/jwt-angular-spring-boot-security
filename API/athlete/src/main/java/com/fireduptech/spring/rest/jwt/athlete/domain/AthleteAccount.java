package com.fireduptech.spring.rest.jwt.athlete.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.*;


@Entity( name = "AthleteAccount" )
@Table( name = "athlete_account" )
public class AthleteAccount {


    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private int id;

    @Id
    private String email;


    @Column( name = "first_name" )
    private String firstName;

    @Column( name = "last_name" )
    private String lastName;

    @Column( name = "account_type" )
    private String accountType;

    @Column( name = "primary_activity" )
    private String primaryActivity;


    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column( name = "registration_date" )
    private LocalDate registrationDate;   // = LocalDate.now()


    public AthleteAccount() {
        this.registrationDate = LocalDate.now();
    }


    public AthleteAccount( String email, String firstName, String lastName, String accountType, String primaryActivity ) {


        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.primaryActivity = primaryActivity;

        this.registrationDate = LocalDate.now();
    }



    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail( String email ) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType( String accountType ) {
        this.accountType = accountType;
    }


    public String getPrimaryActivity() {
        return primaryActivity;
    }

    public void setPrimaryActivity( String primaryActivity ){
        this.primaryActivity = primaryActivity;
    }


    @Override
    public String toString() {
/*		return "AthleteAccount [id=" + id + ", email="  + email + ", firstName=" + firstName
						+ ", lastName=" + lastName + ", accountType=" + accountType
						+ ", primaryActivity=" + primaryActivity + "]";*/
        return this.email;
    }


    // Using Business Key Equality - best not to use == as this can end up different between attached and detached objects in Hiberate
    // Best not to use generated identifiers as end up with different states between a persisted object (that has been assigned an identifier) than
    // to a new created instance which will not have any identifier value generated for it yet.
    @Override
    public boolean equals( Object obj ) {

		if ( !( obj instanceof AthleteAccount ) ) {
			return false;
		}

		AthleteAccount ac = (AthleteAccount) obj;
		if ( !ac.getEmail().equals( this.getEmail() ) ) {
			return false;
		}

        return true;
    }

}
