import {RouteInfo} from "./routeinfo";

export interface LoginInfo {
  phoneNumber: string | any;
  pin: string | any;
}

export interface LoginRes {
  userName: string;
  photo: string;
  userType: string;
}

export interface LoginContext {
  loginRes: LoginRes;
  portalMenuItems: RouteInfo[]
}