import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";
import {Router} from "@angular/router";
import {DataContextService} from "../../shared/data-context.service";
import {TransactionReq} from "../../shared/models/transaction";

@Component({
  selector: 'app-cash-in',
  templateUrl: './cash-in.component.html',
  styleUrls: ['./cash-in.component.scss']
})
export class CashInComponent implements OnInit {

  hidePin: boolean = true;

  cashInForm = this.fb.group({
    cuAccNo: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])],
    amount: ['', Validators.compose([Validators.required, Validators.maxLength(10), Validators.minLength(1)])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  },);

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router,
              private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
  }

  confirmCashIn() {

    let transactionReq: TransactionReq = {
      fromAccNo: this.dataContextService.phoneNumber,
      toAccNo: this.cashInForm.value.cuAccNo,
      txnAmount: this.cashInForm.value.amount,
      txnType: `CASH_IN`,
      reference: ``,
      pin: this.cashInForm.value.pin
    }

    this.dfsHttpServiceService.transaction(transactionReq)
      .subscribe(res => {
        this.dataContextService.transactionResultNextRout = `/agent/cash-in`;
        this.router.navigate([`/agent/txn-result`], {state: {data: res}})
      });
  }

}
