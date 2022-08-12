import {Component, Input, OnInit} from '@angular/core';
import {LoginRes} from "../models/login";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input() loginRes: LoginRes = {
    userName: "",
    photo: "",
    userType: "",
    phoneNumber: "",
    errorMessage: ""
  }

  constructor() {
  }

  ngOnInit(): void {
  }

}
