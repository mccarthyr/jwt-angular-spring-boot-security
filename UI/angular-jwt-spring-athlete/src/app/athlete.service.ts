import { Injectable }                              from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Observable, ObservableInput }             from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class AthleteService {

  private server:string = "http://localhost:8080";


  constructor( private http: Http ) { }


  // *** TODO ---> IMPLEMENT
  public getAthlete( id: number ) {

  }


  // *** TODO ---> IMPLEMENT
  private getAuthHeader() {

  }


  // *** TODO ---> IMPLEMENT
  private extractData(res: Response) {

  }




}
