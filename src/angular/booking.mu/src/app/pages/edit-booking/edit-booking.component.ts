import {Component, OnInit} from '@angular/core';
import {BookingService} from "../../services/booking.service";
import {OfficeService} from "../../services/office.service";
import {EmployeeService} from "../../services/employee.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-edit-booking',
  templateUrl: './edit-booking.component.html',
  styleUrls: ['./edit-booking.component.scss']
})
export class EditBookingComponent implements OnInit {

  booking: any;
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

  constructor(private route: ActivatedRoute, private bookingService: BookingService, private officeService: OfficeService, private employeeService: EmployeeService) {
    this.showList = false;
    this.checkEquipments = [false, false, false];
    this.checkParticipants = [false, false];
    this.equipmentList = [{id: 0, name: 'Projector'}, {id: 1, name: 'Catering'}, {id: 2, name: 'Board'}];
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.bookingService.getBookingsById(+params.get('id')).subscribe((data) => {
        this.booker = data[0].booker;
        this.date = new Date(data[0].date).toISOString();
        this.stime = data[0].stime;
        this.etime = data[0].etime;
        this.selectedOffice = data[0];
        this.participantsList = data[0].participants.split(",");
        this.selectedEquipments = data[0].equipments.split(",");
        for (let i = 0; i < this.equipmentList.length; i++) {
          if (this.selectedEquipments.includes(this.equipmentList[i].name))
            this.checkEquipments[i] = true;
        }
        console.log(this.participantsList);
      });
    });

    this.officeService.getOffices().subscribe((data) => {
        this.officeList = data;
      }
      , error => (console.log(error))
    );
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
      eid: 9,
      name: this.selectedOffice.name,
      booker: "Elon Musk",
      equipments: this.equipments,
      participants: this.participants
    };
    this.bookingService.updateBooking(booking).subscribe(() => {
      window.alert('Booking updated successfully.');
    }, error => {
      window.alert("Booking update was not successful.")
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
    if (this.participantsList.includes(value.name))
      this.participantsList.splice(this.participantsList.indexOf(value.name), 1);
    else
      this.participantsList.push(value.name);
    console.log(this.participantsList);
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

  onUpdateDate(date: Date) {
    this.date = new Date(date).toISOString();
  }
}
