import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { UploadImageService } from 'src/app/services/upload-image.service';
import { EditProductService } from 'src/app/services/edit-product.service';
import { GetProductService } from 'src/app/services/get-product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  private productId: number;
  private product: Product = new Product();
  private productUpdated: boolean;

  constructor(
    private uploadImageService: UploadImageService,
    private editProductService: EditProductService,
    private getProductService: GetProductService,
    private route: ActivatedRoute,
  	private router: Router
  ) { }

  onSubmit() {
  	this.editProductService.sendProduct(this.product).subscribe(
  		data => {
  			this.uploadImageService.modify(JSON.parse(JSON.parse(JSON.stringify(data))._body).id);
  			this.productUpdated=true;
  		},
  		error => {
         console.log(error);
      }
  	);
  }

  ngOnInit() {
  	this.route.params.forEach((params: Params) => {
  		this.productId = Number.parseInt(params['id']);
  	});

  	this.getProductService.getProduct(this.productId).subscribe(
  		res => {
  			this.product = res.json();
  		}, 
  		error => {
        console.log(error);
      }
  	)
  }

}
