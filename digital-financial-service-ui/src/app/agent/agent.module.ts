import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CashInComponent } from './cash-in/cash-in.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { RedeemComponent } from './redeem/redeem.component';



@NgModule({
  declarations: [
    CashInComponent,
    RedeemComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class AgentModule { }
