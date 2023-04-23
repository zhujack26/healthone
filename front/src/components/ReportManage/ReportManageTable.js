/* react */
import { useState, useEffect } from "react";

/* data class */
import ReportInfo from "../../data/ReportInfo";

/* import component */
import ReportTableItem from "./ReportTableItem";

// import server apis //
import { getReportList } from "../../api/ReportInfoAPI";

/* import css */
import "../../assets/css/ReportManageTable.css";
import "../../assets/css/ReportTableItem.css";

/**
 *
 * @param {function} showAnswerModal 답변등록 모달 함수
 * @param {Arrays} tableColumnName 테이블 칼럼 명
 * @returns
 */
const ReportManageTable = ({ showAnswerModal, tableColumnName }) => {
  const reportModalTestData = new ReportInfo(
    1,
    "hong123@gmail.com",
    "로그인이 안돼요",
    "2023. 04. 12.",
    "로그인/회원가입"
  );

  const [reportTableItem, setReportTableItem] = useState([]);

  useEffect(() => {
    getReportList().then((res) => {
      console.dir(res);
      setReportTableItem(
        res.map((reportInfoData, i) => (
          <ReportTableItem
            ReportInfo={reportInfoData}
            showAnswerModal={() => {
              showAnswerModal();
            }}
            key={i}
          />
        ))
      );
    });
  }, []);

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