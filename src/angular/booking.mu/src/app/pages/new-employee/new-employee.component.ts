import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../services/employee.service";

@Component({
  selector: 'app-new-employee',
  templateUrl: './new-employee.component.html',
  styleUrls: ['./new-employee.component.scss']
})
export class NewEmployeeComponent implements OnInit {
  id: any;
  name: any;
  age: any;
  position: any;
  role: any;


  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit(): void {

  }

  bookItem() {

    const employee: any = {
      id: null,
      name: this.name,
      age: this.age,
      position: this.position,
      role: this.role
    };
    this.employeeService.addEmployee(employee).subscribe(() => {
      window.alert('Employee added successfully.');
    }, error => {
      window.alert("Employee addition was not successful.")
    });
  }
}
