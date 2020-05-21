import { Pipe, PipeTransform } from '@angular/core';
import { Cart } from '../models/cart.model';

@Pipe({
  name: 'carts'
})
export class CartsPipe implements PipeTransform {

  transform(data: Cart[],productId:number) {
    
    for (let i in data) {
      if (data[i].product.productId==productId) {
        return true;
      }
    }
    return false;
  }
}
