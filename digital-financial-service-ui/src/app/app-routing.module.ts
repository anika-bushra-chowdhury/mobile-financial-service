import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./auth-module/login/login.component";
import {BToBComponent} from "./admin/b-to-b/b-to-b.component";
import {PortalComponent} from "./custom-layout/portal/portal.component";
import {RegistrationComponent} from "./auth-module/registration/registration.component";
import {SearchUserComponent} from "./admin/search-user/search-user.component";
import {UserDetailsComponent} from "./shared/user-details/user-details.component";
import {CheckBalanceComponent} from "./shared/check-balance/check-balance.component";
import {TransactionResultComponent} from "./shared/transaction-result/transaction-result.component";
import {CashInComponent} from "./agent/cash-in/cash-in.component";
import {PToPComponent} from "./customer/p-to-p/p-to-p.component";
import {CashOutComponent} from "./customer/cash-out/cash-out.component";
import {RedeemComponent} from "./agent/redeem/redeem.component";
import {TxnHistoryComponent} from "./shared/txn-history/txn-history.component";
import {AdminTxnHistoryComponent} from "./admin/admin-txn-history/admin-txn-history.component";

const routes: Routes = [
  {
    path: ``,
    redirectTo: `login`,
    pathMatch: `full`
  },
  {
    path: `login`,
    component: LoginComponent
  },
  {
    path: `user-registration`,
    component: RegistrationComponent
  },
  {
    path: `admin`,
    component: PortalComponent,
    children: [
      {path: `check-balance`, component: CheckBalanceComponent},
      {path: `b2b`, component: BToBComponent},
      {path: `user-txn-history`, component: AdminTxnHistoryComponent},
      {path: `user-registration`, component: RegistrationComponent},
      {path: `search-user`, component: SearchUserComponent},
      {path: `user-details`, component: UserDetailsComponent},
      {path: `txn-result`, component: TransactionResultComponent}
    ]
  },
  {
    path: `customer`,
    component: PortalComponent,
    children: [
      {path: `check-balance`, component: CheckBalanceComponent},
      {path: `user-details`, component: UserDetailsComponent},
      {path: `p2p`, component: PToPComponent},
      {path: `cash-out`, component: CashOutComponent},
      {path: `txn-result`, component: TransactionResultComponent},
      {path: `txn-history`, component: TxnHistoryComponent}
    ]
  },
  {
    path: `agent`,
    component: PortalComponent,
    children: [
      {path: `check-balance`, component: CheckBalanceComponent},
      {path: `user-details`, component: UserDetailsComponent},
      {path: `cash-in`, component: CashInComponent},
      {path: `redeem`, component: RedeemComponent},
      {path: `txn-history`, component: TxnHistoryComponent},
      {path: `txn-result`, component: TransactionResultComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
