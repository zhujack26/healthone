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
 * @returns 
 */
const ReportManageTable = ({showAnswerModal}) => {

  const size = 20;
  
  const [reportTableItem, setReportTableItem] = useState([Array(size).fill().map((e, i)=> <ReportTableItem showAnswerModal={()=>{showAnswerModal()}} key={i}/>)]);

  useEffect(() => {}, []);
  return (
    <div className="report-manage-table">
      <table className="report-table">
        <thead>
          <tr>
            <th>코드번호</th>
            <th>이메일</th>
            <th>제목</th>
            <th>등록 날짜</th>
            <th>문의 유형</th>
            <th>처리 상태</th>
            <th>관리</th>
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
