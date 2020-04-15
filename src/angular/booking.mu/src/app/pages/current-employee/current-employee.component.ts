import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../services/employee.service";

@Component({
  selector: 'app-current-employee',
  templateUrl: './current-employee.component.html',
  styleUrls: ['./current-employee.component.scss']
})
export class CurrentEmployeeComponent implements OnInit {

  loaded: boolean;
  text: any;
  employees: any[];
  employeesCopy: any[];
  showPopup: boolean;
  deleteId;
  type: string;

  constructor(private employeeService: EmployeeService) {
    this.loaded = false;
    this.type = "Employee";
    this.showPopup = false;
  }

  ngOnInit(): void {

    this.employeeService.getEmployees().subscribe((data) => {
        this.loaded = true;
        this.employees = data;
        this.employeesCopy = data;
      }
      , error => (console.log(error))
    );
  }

  onTextChanged(value) {
    this.text = value;
    //search employee
    if (this.text == "") {
      this.employees = this.employeesCopy;
    } else {
      this.employeeService.getEmployeeByName(this.text).subscribe((data) => {
        this.loaded = true;
        this.employees = data;
      });
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
