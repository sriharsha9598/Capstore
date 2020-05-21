import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { HomeComponent } from './components/customer/home/home.component';
import { ProductListComponent } from './components/customer/product-list/product-list.component';
import { ProductLoadingComponent } from './components/customer/product-loading/product-loading.component';
import { ProductDetailsComponent } from './components/customer/product-details/product-details.component';
import { LoadingComponent } from './components/customer/loading/loading.component';
import { WishlistComponent } from './components/customer/wishlist/wishlist.component';
import { PlaceOrderComponent } from './components/customer/place-order/place-order.component';
import { InvoiceComponent } from './components/customer/invoice/invoice.component';
import { AppComponent } from './app.component';


const routes: Routes = [
  { path: '', component: AppComponent},
  { path: 'Customer', component: CustomerComponent, children:[
  { path: '', component: HomeComponent}, 
  { path: 'home', component: HomeComponent },
  { path: 'plist/:subCategory', component: ProductListComponent},
  { path: 'loadProdDetails/:subCategory', component: ProductLoadingComponent},
  { path: 'productDetails/:prodId', component: ProductDetailsComponent},
  { path: 'loadDetails/:prodId', component: LoadingComponent},
  { path: 'wishList', component: WishlistComponent},
  { path: 'order/:id/:quantity/:amount', component:PlaceOrderComponent},
  { path: 'invoice/:data', component:InvoiceComponent}
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
