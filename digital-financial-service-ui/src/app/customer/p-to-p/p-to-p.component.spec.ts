import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PToPComponent } from './p-to-p.component';

describe('PToPComponent', () => {
  let component: PToPComponent;
  let fixture: ComponentFixture<PToPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PToPComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PToPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
