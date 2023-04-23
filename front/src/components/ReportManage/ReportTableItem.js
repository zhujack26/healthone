/* import react */
import { useRef, useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setCurrentReportInfo } from "../../redux/reportList";

/* import component */

/* import css */

import { Button } from "@material-ui/core";
import "../../assets/css/ReportTableItem.css";

/**
 *
 * @param {function} showAnswerModal 모달창 닫는 메서드
 * @param {Object} ReportInfo  불편사항 객체
 * @returns
 */
const ReportTableItem = ({ showAnswerModal, ReportInfo }) => {
  const dispatch = useDispatch();
  // const currentRepoInfo = useSelector((state) => state.reportList.currentReportInfo);

  const options = ["등록완료", "처리접수", "처리완료"];

  const selectRef = useRef();
  const [reportInfo, setReportInfo] = useState(ReportInfo);

  useEffect(() => {
    selectRef.current.value = ReportInfo.reportStatus;
  }, []);

  const handleStatusChange = (e) => {
    alert("불편 사항 상태가 수정되었습니다.");
    const newStatus = options[selectRef.current.selectedIndex];
    const nextReportInfo = { ...reportInfo, reportStatus: newStatus };
    // 서버로 변경사항 보낼 코드
    // TODO...
    setReportInfo(nextReportInfo);
  };

  return (
    <tr className="report-table-item">
      <td>{reportInfo.code}</td>
      <td>
        <div className="long-text">{reportInfo.email}</div>
      </td>
      <td>
        <div className="long-text">{reportInfo.title}</div>
      </td>
      <td>
        <div className="long-text">{reportInfo.regDate}</div>
      </td>
      <td>
        <div className="long-text">{reportInfo.reportType}</div>
      </td>
      <td>
        <select className="report-status-option" ref={selectRef}>
          {options.map((e, i) => (
            <option key={i} value={e}>
              {e}
            </option>
          ))}
        </select>
      </td>
      <td>
        <Button
          className="answer-btn"
          onClick={(e) => {
            dispatch(setCurrentReportInfo(ReportInfo));
            showAnswerModal();
          }}
        >
          답변 등록
        </Button>
        <Button className="save-btn" onClick={handleStatusChange}>
          저장
        </Button>
      </td>
    </tr>
  );
};

export default ReportTableItem;
