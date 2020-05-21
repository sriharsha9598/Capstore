import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { Cart } from 'src/app/models/cart.model';
import { FormBuilder } from '@angular/forms';
import { RaavanTeamService } from 'src/app/services/raavan-team.service';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  title = 'CapStore';
  searchText: string = '';
  product: Product[];
  limit1 = 0
  limit2 = 8
  flag: boolean = false;
  flag1: boolean = false;
  totalAmount:number;
  
  cart:Cart[];
  constructor(private fb: FormBuilder, private listService: RaavanTeamService, private router: Router, private cartService: CartService) { }

  ngOnInit(): void {
    if (this.searchText != '') {
      this.searchProducts();
    }
  }

  searchProducts(): void {

    this.listService.getSearchProducts(this.searchText).subscribe(
      data => {
        console.log();
        this.handler(data);
      }
    )
  }

  subCategory(value: String) {
    this.router.navigate(["/Customer/loadProdDetails/", value]);
  }

  handler(data) {
    this.product = data;

  }

  navigate() {
    this.router.navigate(['/Customer/home']);
  }

  login() {
    alert("login");
    this.flag = true;
  }

  signUp() {
    alert('signUp');
  }
  myOrders() {
    alert("my orders");
  }

  myWishList() {
    this.router.navigate(["/Customer/wishList"])
  }

  // goToCart() {
  //   this.router.navigate(['/cart'])
  // }

  logout() {
    alert("logout");
    this.flag = false;
  }

  old() {
    this.limit1 = Math.min(this.limit1 + 8, this.product.length)
    this.limit2 = Math.min(this.limit2 + 8, this.product.length)
  }

  gotoProduct(prodId: number) {
    this.router.navigate(["/Customer/productDetails/", prodId])
  }

  new() {
    this.limit2 = Math.max(this.limit1, 0)
    this.limit1 = Math.max(this.limit1 - 8, 0)
  }


  myCart(){
    this.flag1=true;
    this.cartService.getCustomerCart("harsha").subscribe(data=>{
      this.cart=data;
      console.log(data);
      this.totalAmount=0;
      for(let i=0;i<this.cart.length;i++){
        this.totalAmount=this.totalAmount+this.cart[i].product.productPrice*this.cart[i].quantity;
      }
      console.log(this.cart);
    },err=>{
      console.log(err.stack);
    })
  }
    increaseCount(i:number){
      //this.quantity[i]=this.quantity[i]+1;
      this.cart[i].quantity=this.cart[i].quantity+1;
      this.totalAmount=this.totalAmount+this.cart[i].product.productPrice;
    }
    decreaseCount(i:number){
      //this.quantity[i]=this.quantity[i]-1;
      if(this.cart[i].quantity==1){
        return;
      }
      this.cart[i].quantity=this.cart[i].quantity-1;
      this.totalAmount=this.totalAmount-this.cart[i].product.productPrice;
    }
    removeProductFromCart(i:number){
      this.cartService.removeProductFromCart(this.cart[i].id).subscribe(data=>{
        if(data){
          alert("product is successfully removed from your cart");
        }
      },err=>{
        console.log(err.stack);
      })
      this.totalAmount=this.totalAmount-this.cart[i].product.productPrice;
      this.cart.splice(i,1);
    }
    checkOut(){
     this.cartService.initializeVariablesForOrder(this.cart,this.totalAmount);
     this.router.navigate(["/Customer/order/",0,0,this.totalAmount]);
    }


}
