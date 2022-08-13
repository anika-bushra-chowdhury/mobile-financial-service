import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthModule} from "./auth-module/auth.module";
import {CustomLayoutModule} from "./custom-layout/custom-layout.module";
import {HttpClientModule} from "@angular/common/http";
import {SharedModule} from "./shared/shared.module";
import {AdminModule} from "./admin/admin.module";
import {AgentModule} from "./agent/agent.module";
import {CustomerModule} from "./customer/customer.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AuthModule,
    CustomLayoutModule,
    HttpClientModule,
    SharedModule,
    AdminModule,
    AgentModule,
    CustomerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
