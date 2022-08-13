import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';


import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {LoginInfo} from './models/login';
import {LoginRes} from "./models/login";
import {HttpErrorHandler, HandleError} from '../http-error-handler.service';
import {User} from "./models/user";
import {BalanceRes} from "./models/balanceRes";
import {TransactionReq, TransactionRes} from "./models/transaction";
import {TxnLogPage, TxnLogReq} from "./txn-history/txnLog";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token'
  })
};

// options: {
//   headers?: HttpHeaders | {[header: string]: string | string[]},
//   observe?: 'body' | 'events' | 'response',
//     params?: HttpParams|{[param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>},
//   reportProgress?: boolean,
//     responseType?: 'arraybuffer'|'blob'|'json'|'text',
//     withCredentials?: boolean,
// }

@Injectable({
  providedIn: 'root'
})
export class DfsHttpServiceService {

  logUrl = 'http://localhost:8085/mobile-financial-service-1.0/api/v1/user/logIn';
  regUrl = 'http://localhost:8085/mobile-financial-service-1.0/api/v1/user/register';
  getUserUrl = 'http://localhost:8085/mobile-financial-service-1.0/api/v1/user/get-user-info/';
  getUserBalanceUrl = 'http://localhost:8085/mobile-financial-service-1.0/api/v1/user/get-balance/';
  transactionUrl = 'http://localhost:8085/mobile-financial-service-1.0/api/v1/txn/doTxn';
  txnHistoryUrl = 'http://localhost:8085/mobile-financial-service-1.0/api/v1/txn/get-user-txn';

  private handleError: HandleError;

  constructor(
    private http: HttpClient,
    httpErrorHandler: HttpErrorHandler) {
    this.handleError = httpErrorHandler.createHandleError('HeroesService');
  }

  getTxnLogPage(txnLogReq: TxnLogReq): Observable<TxnLogPage> {
    return this.http.post<TxnLogPage>(this.txnHistoryUrl, txnLogReq, httpOptions);
  }

  getUser(phoneNumber: string): Observable<User> {
    return this.http.get<User>(this.getUserUrl + phoneNumber);
  }

  getUserBalance(phoneNumber: string): Observable<BalanceRes> {
    return this.http.get<BalanceRes>(this.getUserBalanceUrl + phoneNumber);
  }

  /* GET heroes whose name contains search term */
  searchHeroes(term: string): Observable<LoginInfo[]> {
    term = term.trim();

    // Add safe, URL encoded search parameter if there is a search term
    const options = term ?
      {params: new HttpParams().set('name', term)} : {};

    return this.http.get<LoginInfo[]>(this.logUrl, options)
      .pipe(
        catchError(this.handleError<LoginInfo[]>('searchHeroes', []))
      );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the database */
  addHero(hero: LoginInfo): Observable<LoginInfo> {
    return this.http.post<LoginInfo>(this.logUrl, hero, httpOptions)
      .pipe(
        catchError(this.handleError('addHero', hero))
      );
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(this.regUrl, user, httpOptions)
      .pipe(
        catchError(this.handleError('register', user))
      );
  }

  /** POST: try to log in */
  login(loginInfo: LoginInfo): Observable<LoginRes> {
    return this.http.post<LoginRes>(this.logUrl, loginInfo, httpOptions);
  }

  transaction(transactionReq: TransactionReq): Observable<TransactionRes> {
    return this.http.post<TransactionRes>(this.transactionUrl, transactionReq, httpOptions);
  }

  /** DELETE: delete the hero from the server */
  deleteHero(id: number): Observable<unknown> {
    const url = `${this.logUrl}/${id}`; // DELETE api/heroes/42
    return this.http.delete(url, httpOptions)
      .pipe(
        catchError(this.handleError('deleteHero'))
      );
  }

  /** PUT: update the hero on the server. Returns the updated hero upon success. */
  updateHero(hero: LoginInfo): Observable<LoginInfo> {
    httpOptions.headers =
      httpOptions.headers.set('Authorization', 'my-new-auth-token');

    return this.http.put<LoginInfo>(this.logUrl, hero, httpOptions)
      .pipe(
        catchError(this.handleError('updateHero', hero))
      );
  }
}
