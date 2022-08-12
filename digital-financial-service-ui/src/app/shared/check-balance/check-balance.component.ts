import {Component, OnInit} from '@angular/core';
import {DfsHttpServiceService} from "../dfs-http-service.service";
import {DataContextService} from "../data-context.service";
import {BalanceRes} from "../models/balanceRes";

@Component({
  selector: 'app-check-balance',
  templateUrl: './check-balance.component.html',
  styleUrls: ['./check-balance.component.scss']
})
export class CheckBalanceComponent implements OnInit {

  phoneNumber: string = ``;
  balanceShow: bigint = BigInt(0.0);

  constructor(private dfsHttpServiceService: DfsHttpServiceService, private dataContextService: DataContextService) {
  }

  ngOnInit(): void {
    this.phoneNumber = this.dataContextService.phoneNumber;
    this.dfsHttpServiceService.getUserBalance(this.phoneNumber).subscribe(res => (this.balanceShow = res.balance));
  }

}
