import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {OrdersComponent} from './components/orders/orders.component';

import {OrdersBlockingService} from "./services/orders-blocking.service";
import {OrdersReactiveService} from "./services/orders-reactive.service";

@NgModule({
  declarations: [
    AppComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    OrdersBlockingService,
    OrdersReactiveService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
