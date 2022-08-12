import {Component, Input, OnInit} from '@angular/core';
import {RouteInfo} from "../models/routeinfo";
import {LoginContext, LoginRes} from "../models/login";

declare const $: any;

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  menuItems: RouteInfo[] = [];

  loginRes: LoginRes = {
    userName: "",
    photo: "",
    userType: "",
    phoneNumber: "",
    errorMessage: ""
  }

  @Input() loginContext: LoginContext = {
    loginRes: {
      userName: "",
      photo: "",
      userType: "",
      phoneNumber: "",
      errorMessage: ""
    },
    portalMenuItems: []
  }

  constructor() {
  }

  ngOnInit() {
    this.menuItems = this.loginContext.portalMenuItems;
    this.loginRes = this.loginContext.loginRes;
  }

  isMobileMenu() {
    return $(window).width() <= 991;
  };

}
