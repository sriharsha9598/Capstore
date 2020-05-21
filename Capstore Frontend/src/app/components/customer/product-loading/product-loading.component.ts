import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-loading',
  templateUrl: './product-loading.component.html',
  styleUrls: ['./product-loading.component.css']
})
export class ProductLoadingComponent implements OnInit {

  productId: any;
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => {
      this.productId = params['subCategory'];
    })
  }

  ngOnInit() {
    setTimeout(() => { this.router.navigate(["/Customer/plist/", this.productId]) }, 0);
  }

}
