import { Injectable }                              from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Observable, ObservableInput }             from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { iAthlete } from './iAthlete.type';


@Injectable()
export class AthleteService {

  private server:string = "http://localhost:8080";


  constructor( private http: Http ) { }


  //public getAthlete( id: number ) {}

  //private getAuthHeader() {}


  public getAthleteDetails() : Observable<iAthlete> {

  	let headers = new Headers();
  	headers.append( 'Content-Type', 'application/json' );

  	let currentUser = localStorage.getItem('currentUser');  	// {"user":"athlete1","token":"Bearer eyJhbGciOiJIUzUxMiJ9...."}
  	let authorisedUsernameAndToken = JSON.parse(currentUser);	// [object Object]

	// This extracts the Bearer and it's token value
	console.log("The authorised token is: " + authorisedUsernameAndToken["token"] );

  	headers.append( 'Authorization', authorisedUsernameAndToken["token"] );
  	
	let options = new RequestOptions( { headers: headers} );

	return this.http.get( this.server + "/athleteAccount?acAction=view&athleteAccountId=35", options ).map(
  		(response: Response) => {
  			if ( response.status === 200 ) {

  				console.log( "The successful response text is:  " + response.text() );
  				return response.json();

  			} else {
  				console.log( "Failed retrieval attempt" );
  			}
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


  private extractData( res: Response ) {

  	let content = res.text();
  	if ( content === undefined || content === null || content.length === 0 ) {
  		return {};
  	}

  	let body = res.json();
  	return body || {};
  }


}
