export interface TransactionReq {
  fromAccNo: string | any;
  toAccNo: string | any;
  txnAmount: string | any;
  txnType: string | any;
  reference: string | any;
  pin: string | any;
}

export interface TransactionRes {
  txnType: string;
  reference: string;
  txnAmount: string;
  fee: string;
  commission: string;
  balance: string;
  txnId: string;
  toAccNo: string;
}
