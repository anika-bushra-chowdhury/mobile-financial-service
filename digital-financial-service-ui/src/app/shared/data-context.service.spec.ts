import { TestBed } from '@angular/core/testing';

import { DataContextService } from './data-context.service';

describe('DataContextService', () => {
  let service: DataContextService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataContextService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
