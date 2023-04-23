/* import component */

/* import css */

import { Button } from "@material-ui/core";
import "../../assets/css/ReportTableItem.css";

/**
 *
 * @param {function} showAnswerModal 모달창 닫는 메서드
 * @param {Object} ReportInfoData  불편사항 객체
 * @returns
 */
const ReportTableItem = ({ showAnswerModal, ReportInfoData }) => {
  const options = ["등록완료", "처리접수", "처리완료"];

  return (
    <tr className="report-table-item">
      {Object.values(ReportInfoData).map((e, i) =>
        i != 0 ? (
          <td key={i}>
            <div className="long-text">{e}</div>
          </td>
        ) : (
          <td key={i}>{e}</td>
        )
      )}
      <td>
        <select className="report-status-option">
          {options.map((e, i) => (
            <option key={i}>{e}</option>
          ))}
        </select>
      </td>
      <td>
        <Button
          className="answer-btn"
          onClick={(e) => {
            showAnswerModal();
          }}
        >
          답변 등록
        </Button>
        <Button
          className="save-btn"
          onClick={(e) => {
            alert("변경 사항이 저장되었습니다.");
          }}
        >
          저장
        </Button>
      </td>
    </tr>
  );
};

export default ReportTableItem;
