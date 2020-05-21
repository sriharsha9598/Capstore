import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  baseUrl:string="http://localhost:8094/api/addMoneyToWallet/";
  constructor(private http:HttpClient) { }

  addMoneyToWallet(username:string, balance:number){
    return this.http.put<boolean>(this.baseUrl+username, balance);
  }
}
