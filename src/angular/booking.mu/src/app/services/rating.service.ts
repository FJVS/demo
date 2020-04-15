import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class RatingService {

  constructor(private http: HttpClient) {
    // default constructor
  }

  rateBooking(rating: any): Observable<any> {
    //add to database
    console.log(rating);
    return this.http.post("http://localhost:8080/rating/rateBooking", rating);
  }

}
