export interface TxnLogReq {
  accountNo: string | any,
  fromDate: string | any,
  toDate: string | any,
  pageSize: number | any,
  pageNo: number | any
}

export interface TxnLog {
  serial: number,
  approvalDt: Date,
  approvalDtStr: string,
  txnType: string,
  senderOrReceiver: string,
  debitOrCredit: string,
  txnCategory: string,
  amount: number,
  preBalance: number,
  newBalance: number,
  txnId: string,
  reference: string
}

export interface TxnLogPage {
  content: [TxnLog],
  totalPages: number,
  totalElements: number,
  size: number,
  number: number,
  numberOfElements: number
}
