import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {set} from "../../utils/local-store";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Input() bid: number;
  @Input() showPopupRate: boolean;
  @Output() closePopup: EventEmitter<any>;
  email: any;
  password: any;
  rememberMe: any;
  token: any;

  constructor(private loginService: LoginService, private router: Router) {
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
    this.loginService.loginUser(login).subscribe((token) => {
      // clear history and route to main page
      this.token = token;
      console.log(this.token);
      set('USER_TOKEN', this.token.id_token);
      set('USER_ID', this.token.id_user);
      set('USER_AUTHORITIES', this.token.authorities);
      set('USER_AUTHENTICATED', this.token.authenticated);
      this.router.navigate([""], {replaceUrl: true}).then(() => {
      });
      return false;

    }, error => {
      window.alert("Login not successful. Try again.")
    });
  }

  toggleChecked() {
    this.rememberMe = !this.rememberMe;
  }
}
