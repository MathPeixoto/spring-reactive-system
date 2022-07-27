import { TestBed } from '@angular/core/testing';

import { OrdersReactiveService } from './orders-reactive.service';

describe('OrdersReactiveService', () => {
  let service: OrdersReactiveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrdersReactiveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
