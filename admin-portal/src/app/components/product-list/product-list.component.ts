import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { Product } from 'src/app/models/product';
import { GetProductListService } from 'src/app/services/get-product-list.service';
import { RemoveProductService } from 'src/app/services/remove-product.service';

import {MatDialog, MatDialogRef} from '@angular/material/dialog';
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  private selectedProduct : Product;
	private checked: boolean;
	private productList: Product[];
	private allChecked: boolean;
	private removeProductList: Product[] = new Array();

  constructor(
    private router:Router,
    private getProductListService: GetProductListService,
    private removeProductService: RemoveProductService,
    public dialog:MatDialog
    ) { }

    onSelect(product: Product) {
      this.selectedProduct=product;
      this.router.navigate(['/viewProduct', this.selectedProduct.id]);
    }

    
    openDialog(product: Product) {
      let dialogRef = this.dialog.open(DialogResultExampleDialog);
      dialogRef.afterClosed().subscribe(
        result => {
          console.log(result);
          if(result=="yes") {
            this.removeProductService.sendProduct(product.id).subscribe(
              res => {
                console.log(res);
                this.getProductList();
              }, 
              err => {
                console.log(err);
              }
            );
          }
        }
      );
    }

    getProductList() {
      this.getProductListService.getProductList().subscribe(
        res => {
          console.log(res.json());
          this.productList=res.json();
        }, 
        error => {
          console.log(error);
        }
      );
    }

    updateSelected(checked: boolean) {
      if(checked) {
        this.allChecked = true;
        this.removeProductList=this.productList.slice();
      } else {
        this.allChecked=false;
        this.removeProductList=[];
      }
    }

    updateRemoveProductList(checked:boolean, product: Product) {
      if(checked) {
        this.removeProductList.push(product);
      } else {
        this.removeProductList.splice(this.removeProductList.indexOf(product), 1);
      }
    }

    removeSelectedProducts() {
      let dialogRef = this.dialog.open(DialogResultExampleDialog);
      dialogRef.afterClosed().subscribe(
        result => {
          console.log(result);
          if(result=="yes") {
            for (let product of this.removeProductList) {
              this.removeProductService.sendProduct(product.id).subscribe(
                res => {
  
                }, 
                err => {
                }
                );
            }
            location.reload();
          }
        }
        ); 
    }

    ngOnInit() {
      this.getProductList();
    }

}

@Component({
  selector: 'dialog-result-example-dialog',
  templateUrl: './dialog-result-example-dialog.html'
})
export class DialogResultExampleDialog {
  constructor(public dialogRef: MatDialogRef<DialogResultExampleDialog>) {}
}