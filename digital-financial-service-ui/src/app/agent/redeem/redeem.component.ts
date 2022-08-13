import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";
import {Router} from "@angular/router";
import {DataContextService} from "../../shared/data-context.service";
import {TransactionReq} from "../../shared/models/transaction";

@Component({
  selector: 'app-redeem',
  templateUrl: './redeem.component.html',
  styleUrls: ['./redeem.component.scss']
})
export class RedeemComponent implements OnInit {

  hidePin: boolean = true;

  redeemForm = this.fb.group({
    amount: ['', Validators.compose([Validators.required, Validators.maxLength(10), Validators.minLength(1)])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  },);

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router,
              private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
  }

  confirmRedeem() {

    let transactionReq: TransactionReq = {
      fromAccNo: this.dataContextService.phoneNumber,
      toAccNo: ``,
      txnAmount: this.redeemForm.value.amount,
      txnType: `REDEEM_AG`,
      reference: ``,
      pin: this.redeemForm.value.pin
    }

    this.dfsHttpServiceService.transaction(transactionReq)
      .subscribe(res => {
        this.dataContextService.transactionResultNextRout = `/agent/redeem`;
        this.router.navigate([`/agent/txn-result`], {state: {data: res}})
      });
  }
}
