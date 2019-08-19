import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: Http) { }

  sendCredential(username: string, password: string) {
  	let url = "http://localhost:8181/token";
  	let encodedCredentials = btoa(username+":"+password);
	let basicHeader = "Basic "+encodedCredentials;
	  
	//const headers = new HttpHeaders().set( 'Content-Type','application/x-www-form-urlencoded').set('Authorization', basicHeader);


  	let headers = new Headers ({
  		'Content-Type' : 'application/x-www-form-urlencoded',
  		'Authorization' : basicHeader
  	});

  	return this.http.get(url, {headers: headers});

  }

  checkSession() {
    const url = 'http://localhost:8181/checkSession';
    const xToken = localStorage.getItem('xAuthToken');
    const basicHeader = 'Basic ' + localStorage.getItem('credentials');
    const headers = new Headers({
      'x-auth-token' : xToken,
      'Authorization' : basicHeader
	});
	// let headers = new HttpHeaders();
	//  headers = headers.set('x-auth-token', xToken).set('Authorization', basicHeader);

    console.log(url);
    console.log(headers);
    return this.http.get(url, {headers: headers});
  }

  logout() {
    const url = 'http://localhost:8181/user/logout';
    const xToken = localStorage.getItem('xAuthToken');
    const basicHeader = 'Basic ' + localStorage.getItem('credentials');
    const headers = new Headers({
      'x-auth-token' : xToken,
      'Authorization' : basicHeader
	});
	
	// let headers = new HttpHeaders();
	//  headers = headers.set('x-auth-token', xToken).set('Authorization', basicHeader);


    console.log(url);
    console.log(headers);
    return this.http.post(url, '', {headers: headers});
  }
}
