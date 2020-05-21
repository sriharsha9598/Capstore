import { Injectable } from '@angular/core';
import { CustomerDetails } from '../models/customerDetails.model';
import { Product } from '../models/product';
import { HttpClient } from '@angular/common/http';
import { Cart } from '../models/cart.model';
import { product } from 'src/assets/model';
import { Address } from '../models/address';
import { productFeedback } from '../models/feedback';

@Injectable({
  providedIn: 'root'
})
export class RaavanTeamService {

  pr: product
  constructor(private http: HttpClient) { }

  // endpoint or REST api URL
  baseUrl: string = "http://localhost:8094/api";

  
  // WISHLIST SERVICES

  getAllWishlists(username: String) {
    return this.http.get<Product[]>(this.baseUrl + "/getAllWishlists/" + username);
  }
  removeWishlist(username: String, productId: number) {
    return this.http.get<boolean>(this.baseUrl + "/removeWishlist/" + username + "/" + productId);
  }
  deleteAllWishlists(username: String) {
    return this.http.get<boolean>(this.baseUrl + "/deleteAllWishlists/" + username);
  }
  addWishlist(username: String, productId: number) {
    return this.http.get<boolean>(this.baseUrl + "/addWishlist/" + username + "/" + productId);
  }

  
  setProduct(p: product) {
    this.pr = p;
  }
  getProduct() {
    return this.pr;
  }

  placeOrder(userName: string, prod_id: number, quantity: number, amount: number, addrId: number) {
    return this.http.get<number>(this.baseUrl + "/placeOrder/" + userName + "/" + prod_id + "/" + quantity + "/" + amount + "/" + addrId);
  }
 
   placeCartOrder(username: string, productId: number[], quantity: number[], price: number, addrId: number) {
     return this.http.get<number>(this.baseUrl + "/placeCartOrders/" + "harsha" + "/" + productId + "/" + quantity + "/" + price + "/" + addrId);
   }

  //placeCartOrder(){
    //return this.http.get<number>("http://localhost:8094/api/placeCartOrders/harsha/1001,1002,1003/4,5,6/0/50");
  //}

  getCartOrders(productIds :number[] , quantities : number[]){
    return this.http.get<Product[]> (this.baseUrl+"/getCartOrders/"+productIds+"/"+quantities);
  }

  addNewAddress(address: Address, username:string) {
    return this.http.post(this.baseUrl + "/addAddress/" + username, address);
  }
  getAddressByName(username: string) {
    return this.http.get<Address[]>(this.baseUrl + "/getAddrByName/" + username);
  }
  getCustomer(username: string) {
    return this.http.get<CustomerDetails>(this.baseUrl + "/getCust/" + username);
  }

  validateCoupon(couponCode: String, amount: number) {
    return this.http.get<number>(this.baseUrl + "/validateCoupon/" + couponCode + "/" + amount);
    // return this.http.get<number>(`http://localhost:8094/api/validateCoupon/`+couponCode+`/`+amount);
  }
  getAllCouponsById(id: number) {
    return this.http.get(this.baseUrl+"/getAllCouponsById/" + id);
  }
  getCoupons(ids:number[]){
    return this.http.get(this.baseUrl+"/getCoupons/"+ids)
  }
  getAllBySubCategory(subCategory: string) {
    return this.http.get(`http://localhost:8094/api/getAllProdBySubCategory/${subCategory}`);
  }

  getAllByCategory(category: string) {
    return this.http.get(`http://localhost:8094/api/getAllProdByCategory/${category}`);
  }

  getAll() {
    return this.http.get(`http://localhost:8094/api/getAllProd/`);
  }

  getSearchProducts(searchText: String) {
    return this.http.get(`http://localhost:8094/api/getSearchProducts/${searchText}`)
  }





  // INVOICE SERVICES

  getTransaction(id:number)
  {
  
    return this.http.get(this.baseUrl+"/getTransaction"+"/"+id); 
  }
  getOrders(id:number)
  {

    return this.http.get(this.baseUrl+"/getOrdersForInvoice"+"/"+id); 
  }
  getAddress(id:number)
  {
    
    return this.http.get(this.baseUrl+"/getAddressForInvoice"+"/"+id); 
  }
  getInvoiceCustomer(id:number)
  {
    
    return this.http.get(this.baseUrl+"/getCustomerForInvoice"+"/"+id); 
  }
  getInvoiceProduct(id:number){
    
    return this.http.get(this.baseUrl+"/getProductForInvoice"+"/"+id); 
  }

  addProductToCart(cart:Cart,userName:string){
    return this.http.post<boolean>(this.baseUrl+"/addProductTocart"+"/"+userName,cart);
  }

  // PRODUCT-DETAILS SERVICE
  getProductDetails(productId:number){
    return this.http.get<Product>(this.baseUrl+"/getProd/" + productId);
  }

  getFeedbacksById(productId:number){
    return this.http.get<productFeedback[]>(this.baseUrl+"/getFeedbacksById/"+productId);
  }



  getAllProduct() {
    return this.http.get<Product[]>(this.baseUrl + "/products");
  }
  
  getCustomerCart(userName: string) {
    return this.http.get<Cart[]>(this.baseUrl + "/getCustomerCart" + "/" + userName);
  }
  removeProductFromCart(id: number) {
    return this.http.delete<boolean>(this.baseUrl + "/removeProductFromCart" + "/" + id);
  }

}

