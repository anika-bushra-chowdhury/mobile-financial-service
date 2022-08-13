import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BToBComponent} from './b-to-b/b-to-b.component';
import {SearchUserComponent} from './search-user/search-user.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {ReactiveFormsModule} from "@angular/forms";
import {MatNativeDateModule} from "@angular/material/core";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {SharedModule} from "../shared/shared.module";
import { AdminTxnHistoryComponent } from './admin-txn-history/admin-txn-history.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatDividerModule} from "@angular/material/divider";


@NgModule({
  declarations: [
    BToBComponent,
    SearchUserComponent,
    AdminTxnHistoryComponent,
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    SharedModule,
    MatPaginatorModule,
    MatTableModule,
    MatDatepickerModule,
    MatDividerModule,
  ]
})
export class AdminModule {
}
