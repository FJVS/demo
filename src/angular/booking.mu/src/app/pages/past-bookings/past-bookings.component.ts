import {Component, OnInit} from '@angular/core';
import {BookingService} from "../../services/booking.service";

@Component({
  selector: 'app-past-bookings',
  templateUrl: './past-bookings.component.html',
  styleUrls: ['./past-bookings.component.scss']
})
export class PastBookingsComponent implements OnInit {

  filterList: any[];
  selectedFilter: any;
  showList: any;
  loaded: boolean;
  text: any;
  type: string;
  bookings: any[];
  bookingsCopy: any[];
  showPopup: boolean;
  deleteId;
  showPopupRate: boolean;
  bid;

  constructor(private bookingService: BookingService) {
    this.loaded = false;
    this.showList = false;
    this.type = "Booking";
    this.filterList = [{id: 0, name: 'Booking id'}, {id: 1, name: 'Office id'}, {id: 2, name: 'Employee id'}];
    this.selectedFilter = this.filterList[0];
    this.showPopup = false;
    this.showPopupRate = false;
  }

  ngOnInit(): void {

    this.bookingService.getPastBookings().subscribe((data) => {
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

        this.loaded = true;
        this.bookings = this.bookingsCopy.filter(booking => booking.eid == this.text);
        // this.bookingService.getBookingsByEid(this.text).subscribe((data) => {
        //   this.loaded = true;
        //   this.bookings = data;
        // });

      } else if (this.selectedFilter.name == "Office id") {

        this.loaded = true;
        this.bookings = this.bookingsCopy.filter(booking => booking.oid == this.text);
        // this.bookingService.getBookingsByOid(this.text).subscribe((data) => {
        //   this.loaded = true;
        //   this.bookings = data;
        // });

      } else if (this.selectedFilter.name == "Booking id") {

        this.loaded = true;
        this.bookings = this.bookingsCopy.filter(booking => booking.id == this.text);

        // this.bookingService.getBookingsById(this.text).subscribe((data) => {
        //   this.loaded = true;
        //   this.bookings = data;
        // });

      }
    }
  }

  showDeletePopupRate(booking) {
    this.bid = booking.id;
    this.showPopupRate = true;
  }

  showDeletePopup(id) {
    this.deleteId = id;
    this.showPopup = true;
  }

  closePopup(value: boolean) {
    this.showPopup = value;
    this.showPopupRate = value;
  }

}
