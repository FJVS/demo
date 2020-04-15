import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentEmployeeComponent } from './current-employee.component';

describe('CurrentEmployeeComponent', () => {
  let component: CurrentEmployeeComponent;
  let fixture: ComponentFixture<CurrentEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
