import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-login-home',
  templateUrl: './login-home.component.html',
  styleUrls: ['./login-home.component.css']
})
export class LoginHomeComponent implements OnInit {

  hide: boolean = true;
  logInPressed: boolean = false;

  logInForm = this.fb.group({
    accNo: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
  }

  logIn() {
    this.logInPressed = true;

    if (this.logInForm.status == "VALID") {
      console.warn(this.logInForm.value);
    } else {
    }
  }

}
