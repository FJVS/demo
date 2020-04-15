import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupRateComponent } from './popup-rate.component';

describe('PopupRateComponent', () => {
  let component: PopupRateComponent;
  let fixture: ComponentFixture<PopupRateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopupRateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
