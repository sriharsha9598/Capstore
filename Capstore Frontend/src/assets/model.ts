export class product{
    productId : number;
    productName : string;
    productImage : string;
        productPrice: number;
        productRating : number;

        noOfViews: number;
        productBrand: string;
        noOfProducts: number;
        productInfo: string;
        productActivated: boolean;
        featured: boolean;
}
export class Address{
    addressId : number;
    addressLineOne: string;
    addressLineTwo : string;
    district : string;
    state : string;
    landmark : string;
    
}
export class cart{
    id:number;
    product :product;
    quantity : number;
}
export class Customer
{
    firstName:string;
    lastName:string;
    phone:string;
    email:string;
}
export class Merchant
{
    name:string;
    email:string;
    address:string;
    phone:string;
    
}
export class Coupon{

    couponCode: string;
    couponEndDate: string;
    couponStartDate: string;
    couponAmount: number;
    minOrderAmount:number;
    couponDesc: number;
    issuedBy: string;
    expired: boolean

}

export class Transaction{
    transactionId:number;
    transactionDate:Date;
    transactionMoney:number;

    }

export class Order{
        quantity:number;
        orderAmount:number;
    }