import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BookingService} from "../../services/booking.service";
import {EmployeeService} from "../../services/employee.service";
import {OfficeService} from "../../services/office.service";

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {

  @Input() deleteMessage: string;
  @Input() deleteId: string;
  @Input() showPopup: boolean;
  @Input() type: string;
  @Output() closePopup: EventEmitter<any>;


  constructor(private bookingService: BookingService, private employeeService: EmployeeService, private officeService: OfficeService) {
    this.deleteMessage = '';
    this.deleteId = '';
    this.showPopup = false;
    this.closePopup = new EventEmitter<any>();
  }

  ngOnInit() {
  }

  onCancel() {
    this.showPopup = false;
    this.closePopup.emit(this.showPopup)
  }

  onDelete() {

    if (this.type == "Booking") {
      this.bookingService.deleteBookingById(this.deleteId).subscribe((data) => {
        window.alert("Booking deleted successfully.");
        this.showPopup = false;
        this.closePopup.emit(this.showPopup)
      });
    } else if (this.type == "Employee") {
      this.employeeService.deleteEmployeeById(this.deleteId).subscribe((data) => {
        window.alert("Employee deleted successfully.");
        this.showPopup = false;
        this.closePopup.emit(this.showPopup)
      });
    } else if (this.type == "Office") {
      this.officeService.deleteOfficeById(this.deleteId).subscribe((data) => {
        window.alert("Office deleted successfully.");
        this.showPopup = false;
        this.closePopup.emit(this.showPopup)
      });
    }

  }
}
