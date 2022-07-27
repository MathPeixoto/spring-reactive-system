import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

interface Orders {
  id?: string | number;
  orderStatus?: string;
  responseMessage?: string;
}


@Injectable()
export class OrdersBlockingService {

  url: string = 'http://localhost:8080/api/orders'

  constructor(private http: HttpClient) {}

  getOrders(): Observable<Orders[]> {
    return this.http.get<Orders[]>(this.url)
  }

}
