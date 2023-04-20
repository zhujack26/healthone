/* import component */

/* import css */

import { Button } from "@material-ui/core";
import "../../assets/css/UserTableItem.css";
const UserTableItem = () => {

  const options = ["선택안함", "활성", "휴면", "정지"];

  return (
    <tr className="user-table-item">
      <td>홍길동</td>
      <td>hong123</td>
      <td>hong123@gmail.com</td>
      <td>2023. 04. 19.</td>
      <td>활성</td>
      <td>
        <select className="user-status-option">
            {options.map((e, i) => <option key={i}>{e}</option>)}
        </select>
        <Button className="apply-btn">적용</Button>
        <Button className="cancel-btn">취소</Button>
      </td>
    </tr>
  );
};

export default UserTableItem;
