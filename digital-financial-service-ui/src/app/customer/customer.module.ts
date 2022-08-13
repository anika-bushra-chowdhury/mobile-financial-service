import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PToPComponent } from './p-to-p/p-to-p.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { CashOutComponent } from './cash-out/cash-out.component';



@NgModule({
  declarations: [
    PToPComponent,
    CashOutComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class CustomerModule { }
