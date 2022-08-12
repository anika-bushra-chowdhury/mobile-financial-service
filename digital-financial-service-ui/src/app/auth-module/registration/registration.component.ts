import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";
import {Router} from "@angular/router";
import {map, Observable, startWith} from "rxjs";
import {User} from "../../shared/models/user";
import {LoginRes} from "../../shared/models/login";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  userTypeOptions: string[] = ['Customer', 'Agent'];
  filteredUserTypeOptions: Observable<string[]> | undefined;

  registrationForm = this.fb.group({
    accNo: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])],
    userType: ['', Validators.compose([Validators.required])],
    name: ['', Validators.compose([Validators.required, Validators.maxLength(40), Validators.minLength(2)])],
    nid: ['', Validators.compose([Validators.required, Validators.maxLength(17), Validators.minLength(10)])],
    dob: ['', Validators.compose([Validators.required])],
    address: ['', Validators.compose([Validators.maxLength(250), Validators.required])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])],
    cnfPin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  },);

  hidePin: boolean = true;
  hideCnfPin: boolean = true;
  cnfPinNotMatch: boolean = false;

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

  fromUi: string = `default`;

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router) {
  }

  ngOnInit(): void {

    this.fromUi = history.state.data;

    this.filteredUserTypeOptions = this.registrationForm.valueChanges.pipe(
      startWith(''),
      map((value: any) => this._filter(value || '')),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.userTypeOptions.filter(option => option.toLowerCase().includes(filterValue));
  }

  registration() {
    if (this.registrationForm.status == "VALID") {
      this.cnfPinNotMatch = false;

      if (this.registrationForm.value.pin === this.registrationForm.value.cnfPin) {

        let resolvedUserType: string = ``;
        if (this.registrationForm.value.userType === 'Customer') {
          resolvedUserType = `CUSTOMER`;
        } else if (this.registrationForm.value.userType === 'Agent') {
          resolvedUserType = `AGENT`;
        } else if (this.registrationForm.value.userType === 'Admin') {
          resolvedUserType = `SYSTEM`;
        }

        this.user = {
          phoneNumber: this.registrationForm.value.accNo,
          userType: resolvedUserType,
          userName: this.registrationForm.value.name,
          nid: this.registrationForm.value.nid,
          dob: this.registrationForm.value.dob,
          presAddress: this.registrationForm.value.address,
          pin: this.registrationForm.value.pin,
          userStatus: null,
          profileType: null
        }

        this.dfsHttpServiceService.register(this.user)
          .subscribe(res => (this.user = res));

        let routPath: string = ``;
        if (this.user.userType === 'CUSTOMER') {
          routPath = `customer`;
        } else if (this.user.userType === 'AGENT') {
          routPath = `agent`;
        } else if (this.user.userType === 'SYSTEM') {
          routPath = `admin`;
        }

        let loginRes: LoginRes = {
          userName: this.user.userName,
          photo: "",
          userType: this.user.userType,
          phoneNumber: this.user.phoneNumber,
          errorMessage: ""
        };

        if (this.fromUi === `from-home`) {
          this.router.navigate([routPath], {state: {data: loginRes}})
        } else {
          this.router.navigate(['/admin/search-user'])
        }

      } else {
        this.cnfPinNotMatch = true;
      }
    }
  }
}
