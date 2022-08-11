import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {User} from "../../shared/models/user";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.scss']
})
export class SearchUserComponent implements OnInit {

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

  searchUserForm = this.fb.group({
    phoneNumber: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])]
  });

  constructor(private dfsHttpServiceService: DfsHttpServiceService, private fb: FormBuilder) {
  }

  ngOnInit(): void {
  }

  search() {

    this.user = {
      phoneNumber: this.searchUserForm.value.phoneNumber,
      userType: ``,
      userName: ``,
      nid: ``,
      dob: ``,
      presAddress: ``,
      pin: ``,
      userStatus: ``,
      profileType: ``
    }

    this.dfsHttpServiceService.getUser(this.user.phoneNumber).subscribe(res => (this.user = res));
  }

}
