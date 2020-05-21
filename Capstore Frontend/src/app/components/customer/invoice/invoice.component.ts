import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { UserService1Service } from 'src/app/services/user-service1.service';
//  import * as html2pdf from 'html2pdf.js' ;
import { Order } from 'src/app/models/Order';
import { Transaction } from 'src/app/models/transaction';
import { Product } from 'src/app/models/product';
import { Address } from 'src/app/models/address';
import { CustomerDetails } from 'src/app/models/customerDetails.model';
@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  showInvoice:boolean=false;
  todaysDate = new Date();
  products:Product;
  transactionId:number;
  addresses:Address;
  customers:CustomerDetails;
  orders:Order;
  transactions:Transaction;
  constructor(private route:ActivatedRoute,private userService:UserService1Service,private router:Router) { 
    this.route.params.subscribe(params=>{
      this.transactionId=params['data']

     console.log(this.transactionId)
  })

  }

  ngOnInit() {
    this.userService.getTransaction(this.transactionId).subscribe(data=>{this.handler(data);},
    err=>{
      console.log(err.stack);
    }
    )
    this.userService.getOrders(this.transactionId).subscribe(data=>{
      
      this.handler1(data);},
    err=>{
      console.log(err.stack);
    }
    )
    this.userService.getProduct(this.transactionId).subscribe(data=>{
     
      this.handler2(data);},
    err=>{
      console.log(err.stack);
    }
    )
    this.userService.getAddress(this.transactionId).subscribe(data=>{
    
      this.handler3(data);},
    err=>{
      console.log(err.stack);
    }
    )
    this.userService.getCustomer(this.transactionId).subscribe(data=>{
    
      this.handler4(data);},
    err=>{
      console.log(err.stack);
    }
    )

    setTimeout(() => {
      this.showInvoice=true;
    }, 3500);
  }
handler(data)
{
  this.transactions=data;
  console.log("Data Received")
  console.log("Data Received")
  console.log("Data Received")
  console.log("Data Received")
  console.log("Data Received")
  console.log(this.transactions.transactionMoney)
}
handler1(data)

{
this.orders=data;
}
handler2(data)
{
  this.products=data;
}
handler3(data)
{
  this.addresses=data;
}
handler4(data){
  this.customers=data;
}
// OnExportClick()
// {
//   const options={
// filename:'invoice.pdf',
// image:{type:'jpeg'},
// html2canvas:{},
// jsPDF:{orientation:'landscape'}
//   };

//   const content:Element=document.getElementById('element-to-export');
//   // html2pdf()
//   // .from(content)
//   // .set(options)
//   // .save();
// // var element = document.getElementById('element-to-export');
// // var opt = {
// //   margin:       1,
// //   filename:     'myfile.pdf',
// //   image:        { type: 'jpeg', quality: 0.98 },
// //   html2canvas:  { scale: 2 },
// //   jsPDF:        { unit: 'in', format: 'letter', orientation: 'landscape' }
// // };
 
// // //New Promise-based usage:
// // html2pdf().from(element).set(opt).save();
 
// // // Old monolithic-style usage:
// // // html2pdf(element, opt);
// }
// homeNavigation()
// {
//   this.router.navigate(['/home']);
// }
}
