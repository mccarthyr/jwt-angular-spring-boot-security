import { Component, OnInit, Input }      from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AthleteService }         from '../athlete.service';
import { iAthlete }               from '../iAthlete.type';


@Component({
  selector: 'app-athlete',
  templateUrl: './athlete.component.html',
  styleUrls: ['./athlete.component.css']
})
export class AthleteComponent implements OnInit {


  athleteDetails: iAthlete = null;

  registrationDate: string;
  formattedRegistrationDate: Date;
  

  constructor( 
  	private route: ActivatedRoute,
  	private router: Router,
  	private athleteService: AthleteService
   ) { }


  ngOnInit() {

  	// The link /athlete has already been clicked (once Authenticated) so go direct to it now
  	this.getAthleteDetails();

  }

  private formatRegistrationDate( registrationDate: string ) {

	let day = registrationDate["dayOfMonth"];
	let year = registrationDate["year"];
	let month = registrationDate["monthValue"];

	this.formattedRegistrationDate = new Date( Date.UTC( year, month, day ) );
  }


  public getAthleteDetails() {

  	this.athleteService.getAthleteDetails().subscribe(
  		data => {
  			console.log( "Successfully retrieved the Athlete Details" );
  			this.athleteDetails = data;
  			this.formatRegistrationDate( data.registrationDate );
  		}, 
  		error => {
  			console.log( "There was an error retrieving the Athlete Details: " + error );
  		}
  		);
  }
  /*
	The successful response text is:  
	{"id":35,"email":"mccarthy.richard111@gmail.com","firstName":"Richard","lastName":"McCarthy",
	"accountType":"Richard","primaryActivity":"cycling",
	"registrationDate":{"year":2018,"month":"MARCH","era":"CE","dayOfYear":61,"dayOfWeek":"FRIDAY","leapYear":false,"dayOfMonth":2,"monthValue":3,
						"chronology":{"id":"ISO","calendarType":"iso8601"}}}
  */


}
