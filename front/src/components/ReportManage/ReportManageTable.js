/* import component */
import ReportTableItem from "./ReportTableItem";

/* import css */
import "../../assets/css/ReportManageTable.css";
import "../../assets/css/ReportTableItem.css";

/* import .. */
import { useState, useEffect } from "react";

// reportInfoData
class ReportInfoData {
  constructor(code, email, title, registDate, reportType) {
    this.code = code;
    this.email = email;
    this.title = title;
    this.reportState = registDate;
    this.reportType = reportType;
  }

  // "코드번호", "이메일", "제목", "등록 날짜", "문의 유형", "처리 상태", "관리"
}

/**
 *
 * @param {function} showAnswerModal 답변등록 모달 함수
 * @param {Arrays} tableColumnName 테이블 칼럼 명
 * @returns
 */
const ReportManageTable = ({ showAnswerModal, tableColumnName }) => {
  const reportInfoData = new ReportInfoData(
    1,
    "hong123@gmail.com",
    "로그인이 안돼요",
    "2023. 04. 12.",
    "로그인/회원가입"
  );
  const size = 20;

  const [reportTableItem, setReportTableItem] = useState([
    Array(size)
      .fill()
      .map((e, i) => (
        <ReportTableItem
          ReportInfoData={reportInfoData}
          showAnswerModal={() => {
            showAnswerModal();
          }}
          key={i}
        />
      )),
  ]);

  useEffect(() => {}, []);
  return (
    <div className="report-manage-table">
      <table className="report-table">
        <thead>
          <tr>
            {tableColumnName.map((e, i) => (
              <th key={i}>{e}</th>
            ))}
          </tr>
        </thead>
        <tbody>{reportTableItem}</tbody>
      </table>
    </div>
  );
};

export default ReportManageTable;
