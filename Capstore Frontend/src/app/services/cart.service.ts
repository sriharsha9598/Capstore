import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { Cart } from '../models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  productIds: number[] = [];
  productQuantity: number[] = [];
  toatalAmount: number;
  baseUrl: string = "http://localhost:8094/api/";
  constructor(private http: HttpClient) { }


  getAllProduct() {
    return this.http.get<Product[]>(this.baseUrl + "products");
  }
  getCustomerCart(userName: string) {
    return this.http.get<Cart[]>(this.baseUrl + "getCustomerCart" + "/" + userName);
  }
  removeProductFromCart(id: number) {
    return this.http.delete<boolean>(this.baseUrl + "removeProductFromCart" + "/" + id);
  }
  initializeVariablesForOrder(cart: Cart[], totalPrice: number) {
    for (let i = 0; i < cart.length; i++) {
      this.productIds.push(cart[i].product.productId);
      this.productQuantity.push(cart[i].quantity);
    }
    console.log(this.productIds);
    console.log(this.productQuantity);
    this.toatalAmount = totalPrice;
  }
 
  getProductIds() {
    return this.productIds;
  }
  getProductQuantity() {
    return this.productQuantity;
  }
}





