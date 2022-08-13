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


@NgModule({
  declarations: [
    BToBComponent,
    SearchUserComponent,
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
  ]
})
export class AdminModule {
}
