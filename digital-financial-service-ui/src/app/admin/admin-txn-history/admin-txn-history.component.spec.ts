import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTxnHistoryComponent } from './admin-txn-history.component';

describe('AdminTxnHistoryComponent', () => {
  let component: AdminTxnHistoryComponent;
  let fixture: ComponentFixture<AdminTxnHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminTxnHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTxnHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
