import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckBalanceComponent } from './check-balance.component';

describe('CheckBalanceComponent', () => {
  let component: CheckBalanceComponent;
  let fixture: ComponentFixture<CheckBalanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckBalanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckBalanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
