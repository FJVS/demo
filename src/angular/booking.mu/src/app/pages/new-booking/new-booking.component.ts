import {Component, OnInit} from '@angular/core';
import {BookingService} from "../../services/booking.service";
import {OfficeService} from "../../services/office.service";
import {EmployeeService} from "../../services/employee.service";
import {SharedService} from "../../shared.service";

@Component({
  selector: 'app-new-booking',
  templateUrl: './new-booking.component.html',
  styleUrls: ['./new-booking.component.scss']
})
export class NewBookingComponent implements OnInit {

  bookings: any[];
  officeList: any[];
  equipmentList: any[];
  selectedEquipments = [];
  participantsList = [];
  equipments: any;
  participants: any;
  showList: boolean;
  selectedOffice: any;
  user: any;
  employees: any[];
  date: any;
  stime: any;
  etime: any;
  booker: any;
  checkEquipments: boolean[];
  checkParticipants: boolean[];
  loaded: boolean;
  text: any;
  equipment = "equipment";
  employee = "employee";
  private id: number;

  constructor(private bookingService: BookingService, private officeService: OfficeService,
              private employeeService: EmployeeService, private sharedService: SharedService) {
    this.date = new Date();
    this.booker = "Elon Musk";
    this.showList = false;
    this.checkEquipments = [false, false, false];
    this.checkParticipants = [false, false];
    this.loaded = false;
    this.equipmentList = [{id: 0, name: 'Projector'}, {id: 1, name: 'Catering'}, {id: 2, name: 'Board'}];
  }

  ngOnInit(): void {
    this.officeService.getOffices().subscribe((data) => {
        this.officeList = data;
        this.selectedOffice = this.officeList[0];
      }
      , error => (console.log(error))
    );
    //set menu
    this.sharedService.sendClickEvent();
  }

  bookItem() {
    if (this.selectedEquipments.length == 0)
      this.equipments = "NA";
    else
      this.equipments = this.selectedEquipments.toString();

    if (this.participantsList.length == 0)
      this.participants = "NA";
    else
      this.participants = this.participantsList.toString();

    const booking: any = {
      id: null,
      date: this.date.toString(),
      stime: this.stime,
      etime: this.etime,
      oid: this.selectedOffice.id,
      eid: 33,
      name: this.selectedOffice.name,
      booker: "Elon Musk",
      equipments: this.equipments,
      participants: this.participants
    };
    this.bookingService.addBooking(booking).subscribe(() => {
      window.alert('Booking added successfully.');
    }, error => {
      window.alert("Booking was not successful.")
    });
  }

  updateValue(value: any) {
    this.selectedOffice = value;
  }

  onCheckChanged(value) {
    this.checkEquipments[value.id] = value.boolean;
    this.selectedEquipments = [];
    for (let i = 0; i < this.checkEquipments.length; i++) {
      if (this.checkEquipments[i])
        this.selectedEquipments.push(this.equipmentList[i].name);
    }
  }

  onCheckChangedParticipants(value) {
    if (this.participantsList.length != 0) {
      if (this.participantsList.includes(value.name))
        this.participantsList.splice(this.participantsList.indexOf(value.name), 1);
      else
        this.participantsList.push(value.name);
    } else {
      this.participantsList.push(value.name);
    }
  }

  onTextChanged(value) {
    this.text = value;
    this.checkParticipants = [false, false, false];
    //search employee
    if (this.text == "") {
      this.employees = [];
    } else {
      this.employeeService.getEmployeeByName(this.text).subscribe((data) => {
        this.employees = data;
        for (let i = 0; i < this.employees.length; i++) {
          if (this.participantsList.includes(this.employees[i].name))
            this.checkParticipants[i] = true;
        }
      });
    }
  }

}
