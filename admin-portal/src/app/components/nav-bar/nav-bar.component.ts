import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private loggedIn = false;
  constructor(private loginService: LoginService, private router: Router) { }

  toggleDisplay() {
  	this.loggedIn = !this.loggedIn;
  }

  logout() {
    
    const xToken = localStorage.getItem('xAuthToken');
    if (xToken) {
      
      this.loginService.logout().subscribe(
        res => {
          localStorage.removeItem('xAuthToken');
          localStorage.removeItem('credentials');
          //localStorage.removeItem(xToken);
          console.log("Good")
          
          location.reload();
        },
        error => {
          console.log("error = " + error)
          
        }
      );
    }
    this.router.navigate(['/']);
  }

  ngOnInit() {
    const xToken = localStorage.getItem('xAuthToken');
    if (xToken) {
      this.loginService.checkSession().subscribe(
        res => {
          console.log("Good")
          this.loggedIn = true;
        },
        error => {
          console.log("error = " + error)
          this.loggedIn = false;
        }
      );
    }
  }

}
