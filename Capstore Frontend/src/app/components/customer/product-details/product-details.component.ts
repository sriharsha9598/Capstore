import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { Router } from '@angular/router';
import { Cart } from 'src/app/models/cart.model';
import { RaavanTeamService } from 'src/app/services/raavan-team.service';
import { productFeedback } from 'src/app/models/feedback';
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  feedbacks: productFeedback[]
  productId: number
  discountedPrice: number
  star: string;
  statusMsg: string
  quantity: number = 1;
  show: boolean = false;
  prod: Product
  availability: string = "waiting"
  views: number;
  productDesc: string[];
  similarProd: Product[];
  wishlisted: boolean = false;
  inCart: boolean = false;
  product: Product
  cart: Cart

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private service: RaavanTeamService) {
    this.route.params.subscribe(params => {
      this.productId = params['prodId'];
    })
  }

  ngOnInit() {
    this.service.getProductDetails(this.productId).subscribe(data => {
      this.prod = data;
      this.views = data.noOfViews
      this.star = data.productRating.toString();
      console.log(data.discount + "is the discount")
      if (data.discount != 0) {
        this.discountedPrice = parseInt((data.productPrice - (data.discount / 100) * data.productPrice).toString(), 0);
      }
      else {
        this.discountedPrice = data.productPrice;
      }
      this.productDesc = data.productInfo.split(";")
      if (data.noOfProducts >= 10) {
        this.availability = "In-Stock"
      }
      else
        this.availability = "Only " + data.noOfProducts + " left !!"
    },
      err => {
        console.log("false")
        console.log(err.error)
      })

    this.http.get<Product[]>("http://localhost:8094/api/getSimilarProd/" + this.productId).subscribe(data => {
      console.log("similar true");
      this.similarProd = data;
      console.log(data);
    }, err => { console.log(err) })

    this.getWishlists();
    this.getCartProducts();
    this.service.getFeedbacksById(this.productId).subscribe(data => {
      this.feedbacks = data;
    },
      err => {
        console.log(err.stack)
      })

  }

  showTrue() {
    this.show = true;
    document.getElementById("reviews").style.borderBottom = "3px solid #353c4e";
    document.getElementById("desc").style.borderBottom = "none";
  }

  showFalse() {
    this.show = false;
    document.getElementById("desc").style.borderBottom = "3px solid #353c4e";
    document.getElementById("reviews").style.borderBottom = "none";
  }

  buyNow() {
    if (this.quantity >= 1 && this.quantity <= this.prod.noOfProducts) {
      this.router.navigate(["Customer/order/", this.productId, this.quantity, (this.discountedPrice * this.quantity)]);
    }
  }

  gotoProduct(prodId: number) {
    this.router.navigate(["/Customer/loadDetails/", prodId])
  }

  addToWishList(prodId: number) {
    this.service.addWishlist("Prithve", prodId).subscribe(data => {
      console.log("added to wishlist");
      this.wishlisted = true;
      setTimeout(() => {
        this.statusMsg = "\u2713" + " Item added to Wishlist"
      }, 500);
      setTimeout(() => {
        this.statusMsg = "";
      }, 3000);
    },
      err => {
        console.log(err);
      })
  }

  removeFromWishList(prodId: number) {
    this.service.removeWishlist("Prithve", prodId).subscribe(data => {
      console.log("Removed from wishlist");
      this.wishlisted = false;
      setTimeout(() => {
        this.statusMsg = "Item is removed from Wishlist!!"
      }, 500);
      setTimeout(() => {
        this.statusMsg = ""
      }, 2500);
    },
      err => {
        console.log(err);
      })
  }

  getWishlists() {
    this.service.getAllWishlists("Prithve").subscribe(data => {
      console.log(data);
      for (let i in data) {
        if (data[i].productId == this.productId) {
          this.wishlisted = true;
        }
      }
    },
      err => {
        console.log(err.stack);
      });
  }

  addToCart() {
    this.cart = new Cart();
    this.cart.setQuantity();
    this.cart.setProduct(this.prod);
    this.service.addProductToCart(this.cart, "harsha").subscribe(data => {
      console.log(data);
      if (data) {
        this.inCart = true;
        setTimeout(() => {
          this.statusMsg = "\u2713 Product is added to your cart successfully.."
        }, 500);
        setTimeout(() => {
          this.statusMsg = ""
        }, 3000);
      }
    },
      err => {
        console.log(err.stack);
      })
  }

  getCartProducts() {
    this.service.getCustomerCart("harsha").subscribe(data => {
      console.log("Received cart products")
      console.log(data)
      for (let i in data) {
        if (data[i].product.productId==this.productId) {
          this.inCart = true;
        }
      }
    },
      err => {
        console.log(err.stack)
      });
  }
}
