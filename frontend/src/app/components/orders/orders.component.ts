import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {OrdersBlockingService} from '../../services/orders-blocking.service';
import {OrdersReactiveService} from '../../services/orders-reactive.service';
import {environment} from '../../../environments/environment';

interface Orders {
  id?: string | number;
  orderStatus?: string;
  responseMessage?: string;
}

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  form: FormGroup | undefined;
  response: any;
  error: any;
  previousOrders: Observable<Orders[]>;
  itemList: any;
  paymentModes: any;

  constructor(public fb: FormBuilder, private http: HttpClient,
              private ordersBlockingService: OrdersBlockingService,
              private ordersReactiveService: OrdersReactiveService) {
    this.paymentModes = this.fetchPaymentModes();
    this.fetchProducts().then(() => this.form = this.createForm());
  }

  ngOnInit() {
    this.response = null;
    this.error = null;
    this.previousOrders = new Observable<Orders[]>();
  }

  createForm() {
    const fb = this.fb;
    const form = fb.group({
      userId: 'Bob Marley',
      paymentMode: [this.paymentModes[0]],
      lineItems: this.fb.array([]),
      shippingAddress: this.fb.group({
        name: ['Bob Marley'],
        house: ['24'],
        street: ['Ashford Av.'],
        city: ['New York'],
        zip: ['11001']
      })
    });

    const items = this.itemList;
    items.forEach((value: any, index: string | number) => {
      (form.get('lineItems') as FormArray).push(fb.group({
          productId: items[index].id,
          name: items[index].name,
          stock: items[index].stock,
          quantity: 10
        }
      ));
    });
    return form;
  }

  async fetchProducts() {
    this.itemList = await this.http.get(environment.INVENTORY_URL + '/products').toPromise();
  }

  fetchPaymentModes() {
    return ['Cash on Delivery', 'Card on Delivery'];
  }

  createOrder() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const options = {
      headers
    };

    const handleResponse = (response) => {
      console.log(response);
      this.error = null;
      this.response = response;
    };
    const handleError = (error) => {
      console.log(error);
      this.response = null;
      this.error = error;
    };

    this.http.post(environment.ORDER_URL + '/orders', this.form.value, options)
    .subscribe({next: handleResponse, error: handleError});
  }

  getOrders() {
    this.previousOrders = this.ordersBlockingService.getOrders();
  }

  getOrderStream() {
    this.previousOrders = this.ordersReactiveService.getOrderStream();
  }

}
