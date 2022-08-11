import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BToBComponent } from './b-to-b.component';

describe('BToBComponent', () => {
  let component: BToBComponent;
  let fixture: ComponentFixture<BToBComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BToBComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BToBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
