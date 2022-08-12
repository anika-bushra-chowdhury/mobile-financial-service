import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";
import {LoginRes} from "../../shared/models/login";
import {LoginInfo} from "../../shared/models/login";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hide: boolean = true;
  logInPressed: boolean = false;
  errorMessage: string = "";

  logInForm = this.fb.group({
    accNo: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  });

  loginRes: LoginRes = {
    userName: "",
    photo: "",
    userType: "",
    phoneNumber: "",
    errorMessage: ""
  };

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router) {
  }

  ngOnInit(): void {
  }

  logIn() {
    this.logInPressed = true;

    if (this.logInForm.status == "VALID") {
      const loginInfo: LoginInfo = {
        phoneNumber: this.logInForm.value.accNo,
        pin: this.logInForm.value.pin
      }

      // console.log(loginInfo);

      this.dfsHttpServiceService.login(loginInfo)
        .subscribe(res => (this.loginRes = res));

      console.log(this.loginRes);

      this.errorMessage = this.loginRes.errorMessage;

      if (this.errorMessage === `SUCCESS`) {
        let routPath: string = ``;
        if (this.loginRes.userType === 'CUSTOMER') {
          routPath = `customer`;
        } else if (this.loginRes.userType === 'AGENT') {
          routPath = `agent`;
        } else if (this.loginRes.userType === 'SYSTEM') {
          routPath = `admin`;
        }

        this.router.navigate([routPath], {state: {data: this.loginRes}})
      }
    } else {
    }
  }

  registration() {
    this.router.navigate([`user-registration`], {state: {data: 'from-home'}})
  }

}
