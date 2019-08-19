import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router, NavigationExtras } from '@angular/router';
import {ProductService} from '../../services/product.service';
import {Product} from '../../models/product';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  private loggedIn = false;
  private keyword: string;
  private productList:Product[] =[];

  constructor(
    private loginService: LoginService,
    private router: Router,
    private productService: ProductService
  ) { }

  logout() {
    const xToken = localStorage.getItem('xAuthToken');
    if (xToken) {
      
      this.loginService.logout().subscribe(
        res => {
          localStorage.removeItem('xAuthToken');
          localStorage.removeItem('credentials');
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

  onSearchByTitle() {
    this.productService.searchProduct(this.keyword).subscribe(
      res=> {
        this.productList = res.json();
        console.log(this.productList);
        let navigationExtras: NavigationExtras = {
          queryParams: {
            "productList" : JSON.stringify(this.productList)
          }
        };

        this.router.navigate(['/productList'], navigationExtras);
      },
      error=>{
        console.log(error);
      }
      );
  }

  ngOnInit() {
    const xToken = localStorage.getItem('xAuthToken');
    if (xToken) {
      this.loginService.checkSession().subscribe(
        res => {
		console.log(res);
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