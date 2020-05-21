import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { Router, ActivatedRoute } from '@angular/router';
import { RaavanTeamService } from 'src/app/services/raavan-team.service';
import { CartService } from 'src/app/services/cart.service';
import { Cart } from 'src/app/models/cart.model';
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  limit1 = 0
  limit2 = 8
  subCategory: string;
  product: Product[];
  wishLists: Product[];
  cart1: Cart
  carts: Cart[];
  inCart: boolean = false;
  backUpProduct: Product[];
  searchProd: Product[];
  maxPrice: number = 99999;
  category = ['Electronics', 'Games', 'Home', 'Fashion', 'Books', 'Essentials'];

  constructor(private listService: RaavanTeamService, private router: Router, private route: ActivatedRoute, private us: RaavanTeamService, private cart: CartService) {
    this.route.params.subscribe(params => {
      this.subCategory = params['subCatogory'];
    })
  }

  ngOnInit(): void {
    this.getWishlists();
    if (this.subCategory == 'Electronics' || this.subCategory == 'Games' || this.subCategory == 'Home' || this.subCategory == 'Fashion' || this.subCategory == 'Books' || this.subCategory == 'Essentials') {
      this.listService.getAllByCategory(this.subCategory).subscribe(
        data => {
          console.log(data);
          this.handler(data);
        }
      )
    }
    else {

      this.listService.getAllBySubCategory(this.subCategory).subscribe(
        data => {
          console.log(data);
          this.handler(data);
        }
      )
    }
  }

  handler(data) {
    this.product = data;
    this.searchProd = data;
    //this.maxPrice = data.productPrice;
    console.log(this.maxPrice)
  }

  gotoProduct(prodId: number) {
    this.router.navigate(["/Customer/productDetails/", prodId])
  }

  getWishlists() {
    this.us.getAllWishlists("Prithve").subscribe(data1 => { console.log(data1); this.wishLists = data1 }, err => { console.log(err) })
  }

  addToWishList(prodId: number) {
    this.us.addWishlist("Prithve", prodId).subscribe(data => {
      console.log("added to wishlist");
      this.getWishlists();
    },
      err => {
        console.log(err);
      })
    //this.router.navigate(["//",prodId])
  }

  removeFromWishList(prodId: number) {
    this.us.removeWishlist("Prithve", prodId).subscribe(data => {
      console.log("Removed from wishlist");
      this.getWishlists();
    },
      err => {
        console.log(err);
      })
  }

  removeProductFromCart(prodId: number) {
    this.cart.removeProductFromCart(prodId).subscribe(data => {
      console.log("Removed from cart");
    },
      err => {
        console.log(err);
      })
  }

  old() {

    this.limit1 = Math.min(this.limit1 + 8, this.product.length)
    this.limit2 = Math.min(this.limit2 + 8, this.product.length)

  }


  new() {
    this.limit2 = Math.max(this.limit1, 0)
    this.limit1 = Math.max(this.limit1 - 8, 0)
  }

  sortByLowToHighPrice() {
    this.limit1 = 0
    this.limit2 = 8
    this.sort_by_key(this.product, 'productPrice')
  }

  sortByHighToLowPrice() {
    this.limit1 = 0
    this.limit2 = 8
    this.sort_by_key_desc(this.product, 'productPrice')
  }

  sortByLowToHighDiscount() {
    this.limit1 = 0
    this.limit2 = 8
    this.sort_by_key(this.product, 'discount')
  }

  sortByHighToLowDiscount() {
    this.limit1 = 0
    this.limit2 = 8
    this.sort_by_key_desc(this.product, 'discount')
  }

  sort_by_key(array, key) {
    return array.sort(function (a, b) {
      var x = a[key]; var y = b[key];
      return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    });
  }

  sort_by_key_desc(array, key) {
    return array.sort(function (a, b) {
      var x = a[key]; var y = b[key];
      return ((x < y) ? 1 : ((x > y) ? -1 : 0));
    });
  }

  showFeatured() {
    this.limit1 = 0
    this.limit2 = 8
    let filterResult: any = this.product.filter(u =>
      u.featured)
    this.backUpProduct = this.product
    this.product.splice(0, 0)
    this.product = filterResult
  }

  getBackAllFromFeatured() {
    this.limit1 = 0
    this.limit2 = 8
    this.product = this.backUpProduct
  }

  priceRange(low, high) {
    this.limit1 = 0
    this.limit2 = 8
    let filterResult: any = this.searchProd.filter(u =>
      u.productPrice >= low && u.productPrice <= high)
    this.product.splice(0, 0)
    this.product = filterResult

  }



  addToCart(prodId: number) {
    this.cart1 = new Cart();
    this.cart1.setQuantity();
    this.listService.getProductDetails(prodId).subscribe(data => {
      this.cart1.setProduct(data);
    });
    this.listService.addProductToCart(this.cart1, "harsha").subscribe(data => {
      console.log(data);
      if (data) {
        this.getCartProducts()
      }
    },
      err => {
        console.log(err.stack);
      })
  }

  getCartProducts() {
    this.listService.getCustomerCart("harsha").subscribe(data => {
      console.log("Received cart products")
      console.log(data)
      this.carts = data;
    },
      err => {
        console.log(err.stack)
      });
  }

}
