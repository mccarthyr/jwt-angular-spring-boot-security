import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from './/app-routing.module';
import { AthleteComponent } from './athlete/athlete.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationService } from './authentication.service';
import { AthleteService } from './athlete.service';

import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    AthleteComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule,
    HttpClientModule

  ],
  providers: [AuthenticationService, AthleteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
