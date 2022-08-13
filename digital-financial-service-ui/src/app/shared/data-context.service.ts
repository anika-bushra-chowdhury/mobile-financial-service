import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataContextService {

  private _phoneNumber: string = ``;
  private _transactionResultNextRout: string = ``;
  private _userType: string = ``;

  constructor() {
  }

  set userType(value: string) {
    this._userType = value;
  }

  get userType(): string {
    return this._userType;
  }

  set phoneNumber(value: string) {
    this._phoneNumber = value;
  }

  get phoneNumber(): string {
    return this._phoneNumber;
  }

  get transactionResultNextRout(): string {
    return this._transactionResultNextRout;
  }

  set transactionResultNextRout(value: string) {
    this._transactionResultNextRout = value;
  }

}
