/* import component */

/* import css */

import { Button } from "@material-ui/core";
import "../../assets/css/UserTableItem.css";
const UserTableItem = () => {
  return (
    <div className="user-table-item">
      <div className="name-div">홍길동</div>
      <div className="id-div">hong123</div>
      <div className="email-div">hong123@gmail.com</div>
      <div className="date-div">2023. 04. 19.</div>
      <div className="state-div">활성</div>
      <div className="controll-div">
        <div className="user-controll-frame">
          <select className="controll-select-btn"></select>
          <div className="user-edit-btn-frame">
            <Button className="apply-btn">적용</Button>
            <Button className="cancel-btn">취소</Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserTableItem;
