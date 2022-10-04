import {Injectable, NgZone} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

const EventSource: any = window.EventSource;

@Injectable()
export class OrdersReactiveService {

  url = environment.ORDER_URL + '/orders';

  orders: string[] = [];

  // tslint:disable-next-line:variable-name
  constructor(private _zone: NgZone) {
  }

  getOrderStream() {
    this.orders = [];
    return Observable.create((observer: any) => {
      const eventSource = new EventSource(this.url);
      eventSource.onmessage = (event: any) => {
        console.log('Received event: ', event);
        const json = JSON.parse(event.data);
        this.orders.push(json);
        this._zone.run(() => {
          observer.next(this.orders);
        });
      };
      eventSource.onerror = (error: any) => {
        if (eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
          eventSource.close();
          this._zone.run(() => {
            observer.complete();
          });
        } else {
          this._zone.run(() => {
            observer.error('EventSource error: ' + error);
          });
        }
      };
    });
  }
}
