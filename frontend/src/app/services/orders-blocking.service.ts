import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

interface Orders {
  id?: string | number;
  orderStatus?: string;
  responseMessage?: string;
}

@Injectable()
export class OrdersBlockingService {

  url = environment.ORDER_URL + '/orders';

  constructor(private http: HttpClient) {
  }

  getOrders(): Observable<Orders[]> {
    return this.http.get<Orders[]>(this.url);
  }
}
