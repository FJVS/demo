import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-popup-login',
  templateUrl: './popup-login.component.html',
  styleUrls: ['./popup-login.component.scss']
})
export class PopupLoginComponent implements OnInit {

  @Input() bid: number;
  @Input() showPopupRate: boolean;
  @Output() closePopup: EventEmitter<any>;
  email: any;
  password: any;
  rememberMe: any;

  constructor(private loginService: LoginService) {
    this.showPopupRate = false;
    this.closePopup = new EventEmitter<any>();
    this.rememberMe = false;
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const login: any = {
      email: this.email,
      password: this.password,
      rememberMe: this.rememberMe
    };
    this.loginService.loginUser(login).subscribe(() => {
      // clear history and route to main page
      this.showPopupRate = false;
      this.closePopup.emit(this.showPopupRate)
    }, error => {
      window.alert("Login not successful. Try again.")
    });
  }

  toggleChecked() {
    this.rememberMe = !this.rememberMe;
  }
}
