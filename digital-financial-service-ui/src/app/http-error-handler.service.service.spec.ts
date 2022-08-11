import { TestBed } from '@angular/core/testing';

import { HttpErrorHandlerServiceService } from './http-error-handler.service';

describe('HttpErrorHandlerServiceService', () => {
  let service: HttpErrorHandlerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpErrorHandlerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
