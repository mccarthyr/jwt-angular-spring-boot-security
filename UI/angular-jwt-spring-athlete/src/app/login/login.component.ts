import { Component, OnInit }      from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService }  from '../authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  model: any = {};
  loading: boolean = false;
  returnUrl: string;


  constructor(
  	private route: ActivatedRoute,
  	private router: Router,
  	private authenticationService: AuthenticationService
  	) { }


  ngOnInit() {

  	// Reset the login status
  	this.authenticationService.logout();

  	// Get return url from route parameters or default to '/'
  	this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }



  public login() {
    
  	this.loading = true;

  	this.authenticationService.login( this.model.username, this.model.password )
  		.subscribe(
  			data => {
  				this.router.navigate( [this.returnUrl] );
  				  	this.authenticationService.test()  // NOTE - THIS IS JUST TEMPORARILY HERE - MOVE THIS INTO ITS OWN CALL FROM THE ATHLETE'S LINK...
				  		.subscribe(
				  			data => {
				  				console.log("Successfully returned");
				  			},
				  			error => {
				  				console.log( error );
				  				console.log( "---> THIS ERROR IS COMING FROM THE LOGIN COMPONENT SECTION <---" );
				  				this.loading = false;
				  			}
				  			);

  			},
  			error => {
  				alert(error);
  				console.log( error );
  				console.log( "---> THIS ERROR IS COMING FROM THE LOGIN COMPONENT SECTION <---" );
  				this.loading = false;
  			}
  			);





  }



}


