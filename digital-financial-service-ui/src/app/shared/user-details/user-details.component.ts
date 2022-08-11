import {Component, Input, OnInit} from '@angular/core';
import {User} from "../models/user";
import {DfsHttpServiceService} from "../dfs-http-service.service";
import {DataContextService} from "../data-context.service";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  phoneNumber: string = ``;

  user: User = {
    phoneNumber: "",
    pin: "",
    dob: "",
    nid: "",
    userType: "",
    userName: "",
    presAddress: "",
    userStatus: '',
    profileType: ''
  };

  constructor(private dfsHttpServiceService: DfsHttpServiceService, private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
    this.phoneNumber = this.dataContextService.phoneNumber;
    this.dfsHttpServiceService.getUser(this.phoneNumber).subscribe(res => (this.user = res));
  }

}
