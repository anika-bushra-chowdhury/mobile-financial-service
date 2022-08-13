import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {PageEvent} from "@angular/material/paginator";
import {TxnLog, TxnLogReq} from "./txnLog";
import {FormBuilder} from "@angular/forms";
import {DfsHttpServiceService} from "../dfs-http-service.service";
import {Router} from "@angular/router";
import {DataContextService} from "../data-context.service";

@Component({
  selector: 'app-txn-history',
  templateUrl: './txn-history.component.html',
  styleUrls: ['./txn-history.component.scss']
})
export class TxnHistoryComponent implements OnInit {

  txnHistoryForm = this.fb.group({
    fromDate: [''],
    toDate: [''],
  },);

  totalRows: number | any;
  pageSize: number | any;
  pageIndex: number | any;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  displayedColumns: string[] = ['serial', 'txnType', 'amount', 'debitOrCredit', 'txnCategory', 'senderOrReceiver', 'txnId', 'approvalDtStr', 'reference'];

  dataSource: MatTableDataSource<TxnLog> = new MatTableDataSource();

  constructor(private fb: FormBuilder, private dfsHttpServiceService: DfsHttpServiceService, private router: Router,
              private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
    this.searchTxnHistory(0, 5);
  }

  pageChanged(event: PageEvent) {
    this.searchTxnHistory(event.pageIndex, event.pageSize);
  }

  searchTxnHistory(pageNo: number, pageSize: number) {

    let txnLogRes: TxnLogReq = {
      accountNo: this.dataContextService.phoneNumber,
      fromDate: this.txnHistoryForm.value.fromDate,
      toDate: this.txnHistoryForm.value.toDate,
      pageSize: pageSize,
      pageNo: pageNo
    }

    this.dfsHttpServiceService.getTxnLogPage(txnLogRes)
      .subscribe(res => {
        console.log(res);

        let i: number = 1;

        res.content.forEach((e) => {
          e.serial = i;
          e.txnType = this.getTxnTypeDisName(e.txnType);
          e.debitOrCredit = this.getDorCDisName(e.debitOrCredit);
          e.txnCategory = this.getTxnCatDisName(e.txnCategory);
          e.senderOrReceiver = this.getSorRDisName(e.senderOrReceiver);
          e.approvalDtStr = new Date(e.approvalDt).toString().substring(0, 24);
          e.reference = e.reference === "" ? `-` : e.reference;
          i++;
        });

        this.dataSource.data = res.content;
        this.pageIndex = res.number;
        this.pageSize = res.size;
        this.totalRows = res.totalElements;
      });

  }

  getTxnTypeDisName = function (txnType: string): string | any {

    switch (txnType) {
      case `P2P`: {
        return `P2P`
      }
      case `CASH_OUT`: {
        return `Cash Out`
      }
      case `CASH_IN`: {
        return `Cash In`
      }
      case `B2B_AG`: {
        return `B2B`
      }
      case `REDEEM_AG`: {
        return `Redeem`
      }
    }
  }

  getDorCDisName = function (txnType: string): string | any {

    switch (txnType) {
      case `DEBIT`: {
        return `Debit`
      }
      case `CREDIT`: {
        return `Credit`
      }
    }
  }

  getTxnCatDisName = function (txnType: string): string | any {

    switch (txnType) {
      case `ORIGINAL`: {
        return `Original`
      }
      case `FEE`: {
        return `Fee`
      }
      case `COMMISSION`: {
        return `Commission`
      }
    }
  }

  getSorRDisName = function (txnType: string): string | any {

    switch (txnType) {
      case `SENDER`: {
        return `Sender`
      }
      case `RECEIVER`: {
        return `Receiver`
      }
    }
  }


}
