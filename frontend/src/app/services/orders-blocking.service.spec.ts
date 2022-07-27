import { TestBed } from '@angular/core/testing';

import { OrdersBlockingService } from './orders-blocking.service';

describe('OrdersBlockingService', () => {
  let service: OrdersBlockingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrdersBlockingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
