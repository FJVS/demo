import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentOfficesComponent } from './current-offices.component';

describe('CurrentOfficesComponent', () => {
  let component: CurrentOfficesComponent;
  let fixture: ComponentFixture<CurrentOfficesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentOfficesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentOfficesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
