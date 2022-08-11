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



@NgModule({
  declarations: [
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    UserDetailsComponent
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
        MatCardModule
    ]
})
export class SharedModule { }
