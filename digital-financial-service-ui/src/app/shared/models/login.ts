import {RouteInfo} from "./routeinfo";

export interface LoginInfo {
  phoneNumber: string | any;
  pin: string | any;
}

export interface LoginRes {
  userName: string;
  photo: string;
  userType: string;
  phoneNumber: string;
  errorMessage: string;
}

export interface LoginContext {
  loginRes: LoginRes;
  portalMenuItems: RouteInfo[]
}
