import { Product } from './Product';
import { CustomerDetails } from './customerDetails.model';

export class Cart{
    id:number;
    customer:CustomerDetails;
    product:Product;
    quantity:number;
    setQuantity(){
        this.quantity=1;
    }
    setProduct(product:Product){
        this.product=product;
    }
}