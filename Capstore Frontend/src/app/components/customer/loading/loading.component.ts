import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {

  productId: number;
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => {
      this.productId = params['prodId'];
    })
  }

  ngOnInit() {
    setTimeout(() => { this.router.navigate(["/Customer/productDetails/", this.productId]) }, 0);
  }

}
