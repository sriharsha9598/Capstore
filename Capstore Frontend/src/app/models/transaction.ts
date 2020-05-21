import { Timestamp } from 'rxjs';
import { Order } from './Order';
import { Coupon } from 'src/assets/model';

export class Transaction{
    transactionId:number;
    transactionDate:Date;
    transactionMoney:number;
    transactionMethod:string;
    transactionStatus:string;
    order:Order[];
    coupon:Coupon;
}