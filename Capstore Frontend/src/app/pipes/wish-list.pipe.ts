import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../models/product';

@Pipe({
  name: 'wishList'
})
export class WishListPipe implements PipeTransform {


  transform(data:Product[],productId:number) {
    
    for (let i in data) {
      if (data[i].productId == productId) {
        return true;
      }
    }
    return false;
  }

}
