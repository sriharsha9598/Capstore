import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/customer/home/home.component';
import { ProductListComponent } from './components/customer/product-list/product-list.component';
import { ProductDetailsComponent } from './components/customer/product-details/product-details.component';
import { LoadingComponent } from './components/customer/loading/loading.component';
import { WishlistComponent } from './components/customer/wishlist/wishlist.component';
import { WishListPipe } from './pipes/wish-list.pipe';
import { PlaceOrderComponent } from './components/customer/place-order/place-order.component';
import { ProductLoadingComponent } from './components/customer/product-loading/product-loading.component';
import { InvoiceComponent } from './components/customer/invoice/invoice.component';
import { CartsPipe } from './pipes/carts.pipe';
import { CustomerComponent } from './components/customer/customer.component';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductListComponent,
    ProductDetailsComponent,
    LoadingComponent,
    WishlistComponent,
    WishListPipe,
    PlaceOrderComponent,
    ProductLoadingComponent,
    InvoiceComponent,
    CartsPipe,
    CustomerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
