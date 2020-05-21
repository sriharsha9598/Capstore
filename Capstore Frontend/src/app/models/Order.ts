import { Timestamp } from 'rxjs';
// import { Address } from 'src/assets/model';
import { CustomerDetails } from './customerDetails.model';
import { Product } from './product';
import { Transaction } from './transaction';
import { Address } from './address';

export class Order
{
    orderId:number;
    orderAmount:number;
    oderStatus:string;
    orderDate:Date;
    statusDate:Date;
    transaction:Transaction;
    customer:CustomerDetails;
    address:Address;
    product:Product;
    quantity:number;



}