import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {get} from "../utils/local-store";
import {finalize} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private _redirectUrl: string;

  constructor(private http: HttpClient) {
    // default constructor
    this._redirectUrl = '';
  }

  get redirectUrl(): string {
    return this._redirectUrl;
  }

  set redirectUrl(value: string) {
    this._redirectUrl = value;
  }

  loginUser(login: any): Observable<any> {
    //add to database
    console.log(login);
    return this.http.post("http://localhost:8080/authorize", login);
  }

  public isAuthenticated(): Observable<boolean> {
    return new Observable(observer => {
      const token = get('USER_TOKEN');
      if (!token) {
        observer.next(false);
        observer.complete();
      } else {
        const headers: HttpHeaders = new HttpHeaders({Authorization: `Bearer ${token}`});
        this.http.get(`http://localhost:8080/authenticated`, {headers})
          .pipe(finalize(() => observer.complete()))
          .subscribe(() => {
            observer.next(true);
          }, (err: any) => {
            observer.next(false);
          });
      }
    });
  }

}
