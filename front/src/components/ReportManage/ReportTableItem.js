/* import component */

/* import css */

import { Button } from "@material-ui/core";
import "../../assets/css/ReportTableItem.css";
const ReportTableItem = ({showAnswerModal}) => {

  const options = ["등록완료", "처리접수", "처리완료"];

  return (
    <tr className="report-table-item">
      <td>1</td>
      <td>hong123@gmail.com</td>
          <td>로그인이 안돼요 </td>
          <td>2023. 04. 12.</td>
      <td>로그인/회원가입</td>
          <td>
              <select className="report-status-option">
            {options.map((e, i) => <option key={i}>{e}</option>)}
        </select></td>
      <td>
        <Button className="answer-btn" onClick={(e)=>{showAnswerModal()}}>답변 등록</Button>
        <Button className="save-btn" onClick={(e)=>{alert("변경 사항이 저장되었습니다.")}}>저장</Button>
      </td>
    </tr>
  );
};

export default ReportTableItem;
