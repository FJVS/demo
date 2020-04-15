import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EmployeeService} from "../../services/employee.service";

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.scss']
})
export class EditEmployeeComponent implements OnInit {

  id: any;
  name: any;
  age: any;
  position: any;
  role: any;


  constructor(private route: ActivatedRoute, private employeeService: EmployeeService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.employeeService.getEmployeeById(+params.get('id')).subscribe((data) => {
        this.id = data.id;
        this.name = data.name;
        this.age = data.age;
        this.position = data.position;
        this.role = data.role;
      });
    });
  }

  bookItem() {

    const employee: any = {
      id: this.id,
      name: this.name,
      age: this.age,
      position: this.position,
      role: this.role,
    };
    this.employeeService.updateEmployee(employee).subscribe(() => {
      window.alert('Employee updated successfully.');
    }, error => {
      window.alert("Employee update was not successful.")
    });
  }

}
