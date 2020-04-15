import {Component, OnInit} from '@angular/core';
import {BookingService} from "../../services/booking.service";

@Component({
  selector: 'app-current-bookings',
  templateUrl: './current-bookings.component.html',
  styleUrls: ['./current-bookings.component.scss']
})
export class CurrentBookingsComponent implements OnInit {

  filterList: any[];
  selectedFilter: any;
  showList: any;
  loaded: boolean;
  text: any;
  bookings: any[];
  bookingsCopy: any[];
  showPopup: boolean;
  deleteId;
  type: any;

  constructor(private bookingService: BookingService) {
    this.loaded = false;
    this.type = "Booking";
    this.showList = false;
    this.filterList = [{id: 0, name: 'Booking id'}, {id: 1, name: 'Office id'}, {id: 2, name: 'Employee id'}];
    this.selectedFilter = this.filterList[0];
    this.showPopup = false;
  }

  ngOnInit(): void {

    this.bookingService.getBookings().subscribe((data) => {
        this.loaded = true;
        this.bookings = data;
        this.bookingsCopy = data;
      }
      , error => (console.log(error))
    );
  }

  updateValue(value: any) {
    this.selectedFilter = value;
  }

  onTextChanged(value) {
    this.text = value;
    //search employee
    if (this.text == "") {
      this.bookings = this.bookingsCopy;
    } else {

      if (this.selectedFilter.name == "Employee id") {

        this.bookingService.getBookingsByEid(this.text).subscribe((data) => {
          this.loaded = true;
          this.bookings = data;
        });

      } else if (this.selectedFilter.name == "Office id") {

        this.bookingService.getBookingsByOid(this.text).subscribe((data) => {
          this.loaded = true;
          this.bookings = data;
        });

      } else if (this.selectedFilter.name == "Booking id") {

        this.bookingService.getBookingsById(this.text).subscribe((data) => {
          this.loaded = true;
          this.bookings = data;
        });

      }
    }
  }

  showDeletePopup(id) {
    this.deleteId = id;
    this.showPopup = true;
  }

  closePopup(value: boolean) {
    this.showPopup = value;
  }
}
