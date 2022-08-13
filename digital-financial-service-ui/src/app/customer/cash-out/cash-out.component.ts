import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";
import {Router} from "@angular/router";
import {DataContextService} from "../../shared/data-context.service";
import {TransactionReq} from "../../shared/models/transaction";

@Component({
  selector: 'app-cash-out',
  templateUrl: './cash-out.component.html',
  styleUrls: ['./cash-out.component.scss']
})
export class CashOutComponent implements OnInit {

  hidePin: boolean = true;

  cashOutForm = this.fb.group({
    agAccNo: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])],
    amount: ['', Validators.compose([Validators.required, Validators.maxLength(10), Validators.minLength(1)])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  },);

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router,
              private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
  }

  confirmCashOut() {

    let transactionReq: TransactionReq = {
      fromAccNo: this.dataContextService.phoneNumber,
      toAccNo: this.cashOutForm.value.agAccNo,
      txnAmount: this.cashOutForm.value.amount,
      txnType: `CASH_OUT`,
      reference: ``,
      pin: this.cashOutForm.value.pin
    }

    this.dfsHttpServiceService.transaction(transactionReq)
      .subscribe(res => {
        this.dataContextService.transactionResultNextRout = `/customer/cash-out`;
        this.router.navigate([`/customer/txn-result`], {state: {data: res}})
      });
  }

}
