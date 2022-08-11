import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataContextService {

  private _phoneNumber: string = ``;

  constructor() {
  }

  set phoneNumber(value: string) {
    this._phoneNumber = value;
  }

  get phoneNumber(): string {
    return this._phoneNumber;
  }
}
