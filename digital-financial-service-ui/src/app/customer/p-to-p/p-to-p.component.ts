import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {DfsHttpServiceService} from "../../shared/dfs-http-service.service";
import {Router} from "@angular/router";
import {DataContextService} from "../../shared/data-context.service";
import {TransactionReq} from "../../shared/models/transaction";

@Component({
  selector: 'app-p-to-p',
  templateUrl: './p-to-p.component.html',
  styleUrls: ['./p-to-p.component.scss']
})
export class PToPComponent implements OnInit {

  hidePin: boolean = true;

  pTopForm = this.fb.group({
    toAccNo: ['', Validators.compose([Validators.required, Validators.maxLength(11), Validators.minLength(11), Validators.pattern("(01[3-9]\\d{8})$")])],
    reference: ['', Validators.compose([Validators.required, Validators.maxLength(40), Validators.minLength(1)])],
    amount: ['', Validators.compose([Validators.required, Validators.maxLength(10), Validators.minLength(1)])],
    pin: ['', Validators.compose([Validators.required, Validators.maxLength(4), Validators.minLength(4), Validators.pattern("^(0|[1-9][0-9]*)$")])]
  },);

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router,
              private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
  }

  confirmP2P() {

    let transactionReq: TransactionReq = {
      fromAccNo: this.dataContextService.phoneNumber,
      toAccNo: this.pTopForm.value.toAccNo,
      txnAmount: this.pTopForm.value.amount,
      txnType: `P2P`,
      reference: this.pTopForm.value.reference,
      pin: this.pTopForm.value.pin
    }

    this.dfsHttpServiceService.transaction(transactionReq)
      .subscribe(res => {
        this.dataContextService.transactionResultNextRout = `/customer/p2p`;
        this.router.navigate([`/customer/txn-result`], {state: {data: res}})
      });
  }

}
