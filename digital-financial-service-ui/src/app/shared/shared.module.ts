import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import {MatButtonModule} from "@angular/material/button";
import {RouterModule} from "@angular/router";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import { UserDetailsComponent } from './user-details/user-details.component';
import {MatCardModule} from "@angular/material/card";
import { CheckBalanceComponent } from './check-balance/check-balance.component';
import { TransactionResultComponent } from './transaction-result/transaction-result.component';
import {MatDividerModule} from "@angular/material/divider";
import { TxnHistoryComponent } from './txn-history/txn-history.component';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    UserDetailsComponent,
    CheckBalanceComponent,
    TransactionResultComponent,
    TxnHistoryComponent
  ],
    exports: [
        SidebarComponent,
        NavbarComponent,
        FooterComponent,
        UserDetailsComponent
    ],
  imports: [
    CommonModule,
    MatButtonModule,
    RouterModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    MatDividerModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatInputModule,
    ReactiveFormsModule
  ]
})
export class SharedModule { }
