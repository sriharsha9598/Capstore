import { CustomerDetails } from './customerDetails.model';
import { Product } from 'src/app/models/product';

export class Wishlist{
    id:number;
    product:Product;
    customer:CustomerDetails
}