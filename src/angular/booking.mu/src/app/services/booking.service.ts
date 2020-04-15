import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http: HttpClient) {
    // default constructor
  }

  addBooking(bookingData): Observable<any> {
    //add to database
    console.log(bookingData);
    return this.http.post("http://localhost:8080/booking/add", bookingData);
  }

  updateBooking(bookingData): Observable<any> {
    //add to database
    return this.http.post("http://localhost:8080/booking/update", bookingData);
  }

  getBookings(): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/bookings/get");
  }

  getBookingsByEid(id): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/booking/getByEid?eId=" + id);
  }

  getBookingsByOid(id): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/booking/getByOid?oId=" + id);
  }

  getBookingsById(id): Observable<any> {
    //get data in database
    // const headers: HttpHeaders = new HttpHeaders({'Authorization': `Bearer ${token}`});
    return this.http.get("http://localhost:8080/booking/getById?id=" + id);
  }

  getPastBookings(): Observable<any> {
    //get data in database
    return this.http.get("http://localhost:8080/bookings/getPrevious");
  }

  deleteBookings() {
    //delete all in database
    return this.http.post("http://localhost:8080/bookings/delete", null);
  }

  deleteBookingById(id): Observable<any> {

    return this.http.post("http://localhost:8080/booking/deleteById?id=" + id, null);
  }

}
