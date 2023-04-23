const suffix = "통신중 에러 발생";

let USER_ERROR_MSG = {
  LIST: "회원정보 리스트" + suffix,
  IND: "회원 개별 정보" + suffix,
};

let REPORT_ERROR_MSG = {
  LIST: "불편사항 리스트" + suffix,
  IND: "개별 불편사항 정보" + suffix,
};

export const ERR_USER_LIST = USER_ERROR_MSG.LIST;
export const ERR_USER_IND = USER_ERROR_MSG.IND;
export const ERR_REPORT_LIST = REPORT_ERROR_MSG.LIST;
export const ERR_REPORT_IND = REPORT_ERROR_MSG.IND;
