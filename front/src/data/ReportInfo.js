class ReportInfo {
  constructor(code, email, title, regDate, reportContent = "", reportType = "기타 문의", reportStatus = "등록완료") {
    this.code = code;
    this.email = email;
    this.title = title;
    this.regDate = regDate;
    this.reportContent = reportContent;
    this.reportType = reportType;
    this.reportStatus = reportStatus;
  }
}

export default ReportInfo;
