import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  items = [];
  id;

  constructor(private http: HttpClient) {
    // default constructor
  }

  addEmployee(employee: any): Observable<any> {
    //add to database
    return this.http.post("http://localhost:8080/employee/add", employee);
  }

  updateEmployee(employee: any): Observable<any> {
    //add to database
    console.log(employee);
    return this.http.post("http://localhost:8080/employee/update", employee);
  }

  getEmployees(): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/employees/get");
  }

  getEmployeeByName(name: any): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/employee/getByName?name=" + name);
  }

  getEmployeeById(id: any): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/employee/getById?id=" + id);
  }


  deleteEmployeeById(id): Observable<any> {
    //delete in database by id
    return this.http.post("http://localhost:8080/employee/deleteById?id=" + id, null);
  }

}
