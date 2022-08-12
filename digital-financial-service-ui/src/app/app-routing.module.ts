import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./auth-module/login/login.component";
import {BToBComponent} from "./admin/b-to-b/b-to-b.component";
import {PortalComponent} from "./custom-layout/portal/portal.component";
import {RegistrationComponent} from "./auth-module/registration/registration.component";
import {SearchUserComponent} from "./admin/search-user/search-user.component";
import {UserDetailsComponent} from "./shared/user-details/user-details.component";
import {CheckBalanceComponent} from "./shared/check-balance/check-balance.component";

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
      {path: `user-registration`, component: RegistrationComponent},
      {path: `search-user`, component: SearchUserComponent},
      {path: `user-details`, component: UserDetailsComponent},
    ]
  },
  {
    path: `customer`,
    component: PortalComponent,
    children: [
      {path: `check-balance`, component: CheckBalanceComponent},
      {path: `user-details`, component: UserDetailsComponent}
    ]
  },
  {
    path: `agent`,
    component: PortalComponent,
    children: [
      {path: `check-balance`, component: CheckBalanceComponent},
      {path: `user-details`, component: UserDetailsComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
