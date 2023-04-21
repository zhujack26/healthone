/* import component */
import ReportTableItem from "./ReportTableItem";

/* import css */
import "../../assets/css/ReportManageTable.css";
import "../../assets/css/ReportTableItem.css"

/* import .. */
import { useState, useEffect } from "react";

/**
 * 
 * @param {function} showAnswerModal 답변등록 모달 함수
 * @param {Arrays} tableColumnName 테이블 칼럼 명
 * @returns 
 */
const ReportManageTable = ({showAnswerModal, tableColumnName}) => {

  const size = 20;
  
  const [reportTableItem, setReportTableItem] = useState(
    [Array(size)
      .fill()
      .map((e, i) =>
        <ReportTableItem showAnswerModal={() => { showAnswerModal() }} key={i} />
    )
    ]
  );

  useEffect(() => {}, []);
  return (
    <div className="report-manage-table">
      <table className="report-table">
        <thead>
          <tr>
          {tableColumnName.map((e, i) => <th>{e}</th>)}
          </tr>
        </thead>
        <tbody>
          {reportTableItem}          
        </tbody>
      </table>
    </div>
  );
};

export default ReportManageTable;
