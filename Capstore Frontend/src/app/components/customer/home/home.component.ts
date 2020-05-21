import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }

  subCategory(value: String)
  {
    this.router.navigate(["/Customer/loadProdDetails/",value]);
  }

  category(value: String)
  {
    this.router.navigate(["/Customer/loadProdDetails/",value]);
  }

}
