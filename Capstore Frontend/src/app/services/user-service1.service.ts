import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { Order } from 'src/assets/model';
import { Product } from '../models/product';
import { Order } from '../models/Order';
@Injectable({
  providedIn: 'root'
})
export class UserService1Service {

  constructor(private http:HttpClient) { }
  baseUrl: string = "http://localhost:8094/api";


  getTransaction(id:number)
  {
    return this.http.get(this.baseUrl+"/getTransactionDetails"+"/"+id); 
  }
  getOrders(id:number)
  {
   
    return this.http.get<Order>(this.baseUrl+"/getOrdersForInvoice"+"/"+id); 
  }
  getAddress(id:number)
  {

    return this.http.get(this.baseUrl+"/getAddressForInvoice"+"/"+id); 
  }
  getCustomer(id:number)
  {

    return this.http.get(this.baseUrl+"/getCustomerForInvoice"+"/"+id); 
  }
  getProduct(id:number){

    return this.http.get<Product>(this.baseUrl+"/getProductForInvoice"+"/"+id); 
  }
}
