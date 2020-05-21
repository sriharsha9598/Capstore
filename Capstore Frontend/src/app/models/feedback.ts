import { Product } from './product';
import { CustomerDetails } from './customerDetails.model';

export class productFeedback{
    feedbackId:number;
    feedbackMessage:string;
    feedbackSubject:string;
    rating:number;
    product:Product;
    customer:CustomerDetails;
}