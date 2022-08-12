import {Component, OnInit} from '@angular/core';
import {RouteInfo} from "../../shared/models/routeinfo";
import {LoginContext} from "../../shared/models/login";
import {DataContextService} from "../../shared/data-context.service";

@Component({
  selector: 'app-drawer',
  templateUrl: './portal.component.html',
  styleUrls: ['./portal.component.scss']
})
export class PortalComponent implements OnInit {

  loginContext: LoginContext = {
    loginRes: {
      userName: "",
      photo: "",
      userType: "",
      phoneNumber: ""
    },
    portalMenuItems: []
  }

  constructor(private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
    this.loginContext.loginRes = history.state.data;

    this.dataContextService.phoneNumber = this.loginContext.loginRes.phoneNumber;

    if (this.loginContext.loginRes.userType === 'CUSTOMER') {
      this.loginContext.portalMenuItems = this.cuMenuItems;
    } else if (this.loginContext.loginRes.userType === 'AGENT') {
      this.loginContext.portalMenuItems = this.agMenuItems;
    } else if (this.loginContext.loginRes.userType === 'SYSTEM') {
      this.loginContext.portalMenuItems = this.adminMenuItems;
    }
  }

  cuMenuItems: RouteInfo[] = [
    {path: '/customer/b2b', title: 'B2B', icon: 'dashboard', class: ''},
    {path: '/customer/user-details', title: 'User Profile', icon: 'person', class: ''},
    {path: '/login', title: 'Log Out', icon: 'dashboard', class: ''},
  ];

  agMenuItems: RouteInfo[] = [
    {path: '/agent/b2b', title: 'B2B', icon: 'dashboard', class: ''},
    {path: '/agent/user-details', title: 'User Profile', icon: 'person', class: ''},
    {path: '/agent/table-list', title: 'Table List', icon: 'content_paste', class: ''},
    {path: '/login', title: 'Log Out', icon: 'dashboard', class: ''},
  ];

  adminMenuItems: RouteInfo[] = [
    {path: '/admin/user-details', title: 'User Profile', icon: 'person', class: ''},
    {path: '/admin/search-user', title: 'Search User', icon: 'content_paste', class: ''},
    {path: '/admin/user-registration', title: 'User Registration', icon: 'bubble_chart', class: ''},
    {path: '/admin/b2b', title: 'B2B', icon: 'dashboard', class: ''},
    {path: '/login', title: 'Log Out', icon: 'dashboard', class: ''},
  ];

}