import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {TopBarComponent} from './components/top-bar/top-bar.component';
import {EmployeeService} from './services/employee.service';
import {DropdownComponent} from './components/dropdown/dropdown.component';
import {CheckboxComponent} from './components/checkbox/checkbox.component';
import {SearchComponent} from './components/search/search.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatGridListModule} from "@angular/material/grid-list";
import {NewBookingComponent} from './pages/new-booking/new-booking.component';
import {CurrentBookingsComponent} from './pages/current-bookings/current-bookings.component';
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import {EmployeeComponent} from './pages/employee/employee.component';
import {OfficeComponent} from './pages/office/office.component';
import {PastBookingsComponent} from './pages/past-bookings/past-bookings.component';
import {NewEmployeeComponent} from "./pages/new-employee/new-employee.component";
import {CurrentEmployeeComponent} from './pages/current-employee/current-employee.component';
import {NewOfficeComponent} from './pages/new-office/new-office.component';
import {CurrentOfficesComponent} from './pages/current-offices/current-offices.component';
import {EditBookingComponent} from './pages/edit-booking/edit-booking.component';
import {PopupComponent} from './components/popup/popup.component';
import {EditEmployeeComponent} from './pages/edit-employee/edit-employee.component';
import {EditOfficeComponent} from './pages/edit-office/edit-office.component';
import {PopupRateComponent} from './components/popup-rate/popup-rate.component';
import {LoginComponent} from './pages/login/login.component';
import {PopupLoginComponent} from './components/popup-login/popup-login.component';
import {AuthGuard} from "./guards/auth.guard";

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot([
      {path: '', component: NewBookingComponent, canActivate: [AuthGuard]},
      {path: 'new-booking', component: NewBookingComponent, canActivate: [AuthGuard]},
      {path: 'new-employee', component: NewEmployeeComponent, canActivate: [AuthGuard]},
      {path: 'login', component: LoginComponent, pathMatch: 'full'},
      {path: 'edit-booking/:id', component: EditBookingComponent, canActivate: [AuthGuard]},
      {path: 'edit-employee/:id', component: EditEmployeeComponent, canActivate: [AuthGuard]},
      {path: 'edit-office/:id', component: EditOfficeComponent, canActivate: [AuthGuard]},
      {path: 'new-office', component: NewOfficeComponent, canActivate: [AuthGuard]},
      {path: 'current-bookings', component: CurrentBookingsComponent, canActivate: [AuthGuard]},
      {path: 'past-bookings', component: PastBookingsComponent, canActivate: [AuthGuard]},
      {path: 'current-employees', component: CurrentEmployeeComponent, canActivate: [AuthGuard]},
      {path: 'current-offices', component: CurrentOfficesComponent, canActivate: [AuthGuard]},
      {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
      {path: 'employee', component: EmployeeComponent, canActivate: [AuthGuard]},
      {path: 'office', component: OfficeComponent, canActivate: [AuthGuard]},
    ]),
    BrowserAnimationsModule,
    MatGridListModule
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    DropdownComponent,
    CheckboxComponent,
    SearchComponent,
    NewBookingComponent,
    NewEmployeeComponent,
    CurrentBookingsComponent,
    DashboardComponent,
    EmployeeComponent,
    OfficeComponent,
    PastBookingsComponent,
    CurrentEmployeeComponent,
    NewOfficeComponent,
    CurrentOfficesComponent,
    EditBookingComponent,
    PopupComponent,
    EditEmployeeComponent,
    EditOfficeComponent,
    PopupRateComponent,
    LoginComponent,
    PopupLoginComponent,
  ],
  bootstrap: [AppComponent],
  providers: [EmployeeService]
})
export class AppModule {
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
