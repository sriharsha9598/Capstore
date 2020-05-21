import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { RaavanTeamService } from 'src/app/services/raavan-team.service';
@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  constructor(public router:Router,public us:RaavanTeamService) { }
  products:Product[];
  statusMsg:string=""

  username:String="Prithve";
  ngOnInit() {
    // if (localStorage.username != null) {
      this.getWishlists();
    // }
    // else {
    //   this.router.navigate(['/login']);
    // }
  }
  getWishlists() {
    this.us.getAllWishlists(this.username).subscribe(data => {
      this.products = data;
      console.log(this.products);
    },
      err => {
        console.log(err.stack);
      });
  }

  removeFromWishlist(productId:number){
    this.us.removeWishlist(this.username,productId).subscribe(data=>{
      console.log("removed")
      this.getWishlists()
      this.statusMsg="\u2713 Item "+productId+" removed from wishlist succesfully!!"
      setTimeout(() => {
        this.statusMsg=""
      }, 3000);
    // alert("removed")
    },
    err=>{

    console.log(err.stack);
    });
  }
  addToCart(id:String){

  }

  deleteAll(){
    if(this.products.length != 0)
    {
    if(confirm("Are you Sure You Want to Delete All Wishlists?")){
    this.us.deleteAllWishlists(this.username).subscribe(data=>{
      this.getWishlists()
      alert("All Wishlists Deleted Successfully")
    },
    err=>{
      console.log(err.stack)
    });    
    }
  }
  else{
    alert(`No items in WishList`);
  }
  }
  getProduct(id:number){
    this.router.navigate(['/Customer/productDetails',id]);
  }
}
