import {Component, OnInit} from '@angular/core';
import {DataContextService} from "../data-context.service";
import {TransactionRes} from "../models/transaction";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transaction-result',
  templateUrl: './transaction-result.component.html',
  styleUrls: ['./transaction-result.component.scss']
})
export class TransactionResultComponent implements OnInit {

  transactionRes: TransactionRes = {
    txnType: ``,
    reference: ``,
    txnAmount: `0`,
    fee: `0`,
    commission: `0`,
    balance: `0`,
    txnId: ``,
    toAccNo: ``
  }

  nextRout: string = ``

  constructor(private dataContextService: DataContextService, private router: Router) {
  }

  ngOnInit(): void {
    this.transactionRes = history.state.data;

    switch (this.transactionRes.txnType) {
      case `P2P`: {
        this.transactionRes.txnType = `P2P`;
        this.nextRout = `p2p`
        break;
      }
      case `CASH_OUT`: {
        this.transactionRes.txnType = `Cash Out`;
        this.nextRout = `cash-out`
        break;
      }
      case `CASH_IN`: {
        this.transactionRes.txnType = `Cash In`;
        this.nextRout = `cash-in`
        break;
      }
      case `B2B_AG`: {
        this.transactionRes.txnType = `B2B to Agent`;
        this.nextRout = `b2b`
        break;
      }
      case `REDEEM_AG`: {
        this.transactionRes.txnType = `Agent E-Money Redemption`;
        this.nextRout = `redeem`
        break;
      }
    }
  }

  toAnotherTxn() {
    this.router.navigate([this.dataContextService.transactionResultNextRout])
  }
}
