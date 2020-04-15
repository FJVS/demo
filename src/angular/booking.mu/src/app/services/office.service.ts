import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

  items = [];
  id;

  constructor(private http: HttpClient) {
    // default constructor
  }

  addOffice(office: any): Observable<any> {
    //add to database
    console.log(office);
    return this.http.post("http://localhost:8080/office/add", office);
  }

  updateOffice(office: any): Observable<any> {
    //add to database
    return this.http.post("http://localhost:8080/office/update", office);
  }

  getOffices(): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/offices/get");
  }

  getOfficeByName(name): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/office/getByName?name=" + name);
  }

  getOfficeById(id: any): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/office/getById?id=" + id);
  }

  getOfficeImage(id: any): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/office/getImage?id=" + id);
  }

  deleteOfficeById(id): Observable<any> {

    return this.http.post("http://localhost:8080/office/deleteById?id=" + id, null);
  }

}
