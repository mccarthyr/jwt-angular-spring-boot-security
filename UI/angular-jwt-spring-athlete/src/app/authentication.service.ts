import { Injectable }                                               from '@angular/core';
import { Http, Headers, Response, RequestOptions, URLSearchParams } from '@angular/http';

import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';

import { Observable }                                               from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/throw';


@Injectable()
export class AuthenticationService {

  private server:string = "http://localhost:8080";


  constructor( private http: Http, private httpClient: HttpClient ) { }


//Key is 'currentUser' and Value is {"user":"athlete1","token":"Bearer eyJhbGciOiJIUzUxMiJ9..."}
// **** TODO *** CONSIDER CREATING A TOKEN CLASS TYPE AND MAPPING THAT USING let currentUser: TokenClass = localStorage.getItem....
//				AND THEN SEE IF CAN ACCESS IT USING JSON OBJECT NOTATION INSTEAD - JUST TO SEE IF IT WORKS ???


  public test() {

  	let headers = new Headers();
  	headers.append( 'Content-Type', 'application/json' );

  	let currentUser = localStorage.getItem('currentUser');  // {"user":"athlete1","token":"Bearer eyJhbGciOiJIUzUxMiJ9...."}
  	let authorisedUsernameAndToken = JSON.parse(currentUser);	// [object Object]

	// This extracts the Bearer and it's token value
	console.log("The authorised token is: " + authorisedUsernameAndToken["token"] );

  	headers.append( 'Authorization', authorisedUsernameAndToken["token"] );

	let options = new RequestOptions( { headers: headers} );

	return this.http.get( this.server + "/athleteAccount?acAction=view&athleteAccountId=35", options ).map(
  		(response: Response) => {
  			if ( response.status === 200 ) {

  				console.log( "The successful response text is:  " + response.text() );
  			} else {
  				console.log( "Failed retrieval attempt" );
  			}
  		}
  		);

  }	// END OF METHOD TEST()...




  public login( username: string, password: string ) {

  	const body = new URLSearchParams();

  	body.set( "username", username );
  	body.set( "password", password );

  	let headers = new Headers();
  	headers.append( 'Content-Type', 'application/x-www-form-urlencoded' );

	let options = new RequestOptions( { headers: headers, withCredentials: true } );

	
  	return this.http.post( this.server + "/login", body.toString(), options ).map(
  		(response: Response) => {
  			if ( response.status === 200 ) {

  				console.log( "Successful authentication from Angular to Spring!" );  				
 				console.log( "The Authorisation header value is: " + response.headers.get('Authorization') );
 				 			
  				localStorage.setItem( 'currentUser', JSON.stringify( { user: username, token: response.headers.get('Authorization') } ) );

  			} else {
  				console.log( "Failed authentication attempt" );
  			}
  		}
  		);
  	
  }	// End of method login()...



  public logout() {

  	// Remove user from local storage to log user out
  	localStorage.removeItem( 'currentUser' );
  	return Observable.of( true );
  }



}
