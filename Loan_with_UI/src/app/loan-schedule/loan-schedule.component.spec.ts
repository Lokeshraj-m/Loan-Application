import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanScheduleComponent } from './loan-schedule.component';

describe('LoanScheduleComponent', () => {
  let component: LoanScheduleComponent;
  let fixture: ComponentFixture<LoanScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoanScheduleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
