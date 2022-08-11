import { TestBed } from '@angular/core/testing';

import { DfsHttpServiceService } from './dfs-http-service.service';

describe('DfsHttpServiceService', () => {
  let service: DfsHttpServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DfsHttpServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
