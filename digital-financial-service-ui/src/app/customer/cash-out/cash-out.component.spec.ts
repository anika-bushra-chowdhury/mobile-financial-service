import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CashOutComponent } from './cash-out.component';

describe('CashOutComponent', () => {
  let component: CashOutComponent;
  let fixture: ComponentFixture<CashOutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CashOutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CashOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
