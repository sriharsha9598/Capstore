import { Component, OnInit, Input } from '@angular/core';
// import { product, Address, Coupon } from 'src/assets/model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { RaavanTeamService } from 'src/app/services/raavan-team.service';
import { CartService } from 'src/app/services/cart.service';
import { Coupon, product, Customer } from 'src/assets/model';
import { Address } from 'src/app/models/address';
import { CustomerDetails } from 'src/app/models/customerDetails.model';
import { WalletService } from 'src/app/services/wallet.service';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {

  addressForm: FormGroup;
  quantity: number;
  orderAmount: number;
  coupons?: Coupon[];
  couponAmount: number;
  finalPrice: number;
  appliedCoupon: boolean = false;
  addNewAddress: boolean = false;
  errorMessage: string = '';
  productsList: Product[]
  productId: number = -1;
  addressList: Address[]
  selectedAddress: number = -1;
  pr: product;
  showAddressForm: boolean = false;
  submitted: boolean = false;
  selectedCoupon: String;
  cquantities = [4, 5, 6]
  statusMsg: string = ""
  invalidCoupon: string = ""
  productIdArrayFromCart: number[];
  productQuantityArrayFromCart: number[];
  totalAmount: number;
  insufficientBalance: boolean = false;
  customer: CustomerDetails;
  balance: number;
  depositeForm: FormGroup;
  cardForm: FormGroup;

  constructor(private service: RaavanTeamService, private cartService: CartService, private walletService: WalletService, private route: ActivatedRoute, private formBuilder: FormBuilder, private router: Router) {
    this.route.params.subscribe(params => {
      this.productId = params['id'];
      this.quantity = params['quantity'];
      this.orderAmount = params['amount'];
      this.finalPrice = params['amount'];
    })
  }

  ngOnInit() {
    this.service.getCustomer("harsha").subscribe(data => this.customer = data)
    if (this.productId != 0) {
      this.service.getProductDetails(this.productId).subscribe(data => {
        console.log("got input")
        this.pr = data
      },
        err => {
          console.log(err.error)
        });
    }
    if (this.productId == 0) {

      this.productIdArrayFromCart = this.cartService.getProductIds();
      console.log(this.productIdArrayFromCart);
      this.productQuantityArrayFromCart = this.cartService.getProductQuantity();
      console.log(this.productQuantityArrayFromCart);
      this.totalAmount = this.cartService.toatalAmount;
      console.log(this.totalAmount);
      this.service.getCartOrders(this.productIdArrayFromCart, this.productQuantityArrayFromCart).subscribe(
        data => {
          this.productsList = data;
          for (let i in this.productsList) {
            this.productsList[i].quantity = this.productQuantityArrayFromCart[i];
          }
        },
        err => { }
      )
    }
    this.addressForm = this.formBuilder.group({
      addressLineOne: ['', Validators.required],
      addressLineTwo: ['', Validators.required],
      state: ['', Validators.required],
      district: ['', Validators.required],
      landmark: ['', Validators.required]
    })


    this.depositeForm = this.formBuilder.group({
      addThrough: ['', Validators.required],
      amount: ['', [Validators.required, Validators.pattern("^-?[0-9]\\d*(\\.\\d{1,2})?$"), Validators.min(1)]]
    });
    this.depositeForm.controls.amount.setValue(this.balance);

    this.cardForm = this.formBuilder.group({
      CardNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{16}$")]],
      Pin: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{4}$")]],
      HolderName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
      Cvv: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{3}$")]],
      Month: ['', Validators.required],
      Year: ['', Validators.required]
    });

    this.selectAddress();
    this.getCoupons();
  }

  newAddress() {
    this.showAddressForm = true;
  }

  selectAddress() {
    this.service.getAddressByName("harsha").subscribe(data => {
      this.addressList = data;
    });
  }

  addAddress() {
    this.submitted = true;
    if (this.addressForm.invalid) {
      return;
    }
    else {
      this.showAddressForm = false;
      this.service.addNewAddress(this.addressForm.value, "harsha").subscribe(data => {
        this.statusMsg = "Address added successfully!! Please find it in the list !!"
        setTimeout(() => {
          this.statusMsg = "";
        }, 3000);

        this.selectAddress()
      },
        err => {
          console.log(err.error)
        });
    }
  }


  getCoupons() {
    console.log(this.productId)
    if (this.productId != 0) {
      this.service.getAllCouponsById(this.productId).subscribe(data => {
        this.hr(data);
      }

      )
    }
    if (this.productId == 0) {
      this.service.getCoupons(this.productIdArrayFromCart).subscribe(data => {
        this.hr(data);
      })
    }
  }
  hr(data) {
    this.coupons = data;
    console.log(this.coupons);
  }



  deposit() {
    this.router.navigate(['']);
  }

  pickCoupon() {
    console.log(this.selectedCoupon)
    this.service.validateCoupon(this.selectedCoupon, this.orderAmount).subscribe(data => {
      console.log(data)
      if (data == 0) {
        this.invalidCoupon = "Invalid Coupon";
        setTimeout(() => {
          this.invalidCoupon = ""
        }, 3000);
      }
      else {
        console.log(data)
        this.appliedCoupon = true;
        this.couponAmount = data;
        this.finalPrice = this.orderAmount - this.couponAmount;
      }
    },
      err => {

      });
  }




  placeOrder() {
    if (this.selectedAddress != -1) {
      if (this.customer.balance < (this.orderAmount) || this.customer.balance < this.totalAmount) {
        this.insufficientBalance = true;
      }
      else {
        if (this.productId != 0) {
          this.service.placeOrder("harsha", this.productId, this.quantity, this.orderAmount, this.selectedAddress).subscribe(data => {
            if (data != 0) {
              console.log("placed order successfully")
              this.router.navigate(['/Customer/invoice/', data]);
            }
          },
            err => {
              console.log(err.error)
            });
        }
        if(this.productId ==0) {
          this.service.placeCartOrder("harsha", this.productIdArrayFromCart, this.productQuantityArrayFromCart, this.totalAmount, this.selectedAddress).subscribe(data => {
            console.log(data);
            this.router.navigate(['/Customer/invoice/', data]);
          });
        }
      }
    }
    else {
      this.errorMessage = "Please select an address!"
    }
  }

  addMoneyInWallet() {
    this.balance = parseFloat(this.depositeForm.controls.amount.value);
    console.log(this.balance);
    this.walletService.addMoneyToWallet("harsha", this.balance).subscribe(data => {
      console.log(data);
      if (data) {
        alert("money is successfully added in your wallet.....");
        this.insufficientBalance=false;
        this.customer.balance+=this.balance
      }
    }, err => {
      console.log("err");
      console.log(err.stack);
    })
    this.cardForm.reset();
    this.depositeForm.reset();
  }

  close(){
    this.insufficientBalance=false;
  }


}
