import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataContextService {

  private _phoneNumber: string = ``;
  private _transactionResultNextRout: string = ``;

  constructor() {
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
