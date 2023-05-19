/* import react */
import { useRef, useState } from "react";

/* data class */
// import UserInfo from "../../data/UserInfo.js";

/* import component */

/* import css */

import { Button } from "@material-ui/core";
import "../../assets/css/UserTableItem.css";

/**
 * @param {UserInfo} UserInfo 사용자 관리 정보 담은 객체
 * @returns
 */
const UserTableItem = ({ UserInfo }) => {
  const options = ["활성", "휴면", "정지"];

  const selectRef = useRef();
  const [userInfo, setUserInfo] = useState(UserInfo);

  const handleStatusChange = (e) => {
    alert("회원 상태가 수정되었습니다.");
    const newStatus = options[selectRef.current.selectedIndex];
    const nextUserInfo = { ...userInfo, userStatus: newStatus };
    // 서버로 변경사항 보낼 코드
    // TODO...
    setUserInfo(nextUserInfo);
  };

  return (
    <tr className="user-table-item">
      <td>
        <div className="long-text">{userInfo.userName}</div>
      </td>
      <td>
        <div className="long-text">{userInfo.userEmail}</div>
      </td>
      <td>
        <div className="long-text">{userInfo.joinDate}</div>
      </td>
      <td>{userInfo.userStatus}</td>
      <td>
        <select className="user-status-option" ref={selectRef}>
          {options.map((e, i) => (
            <option key={i} value={e}>
              {e}
            </option>
          ))}
        </select>
        <Button className="apply-btn" onClick={handleStatusChange}>
          적용
        </Button>
      </td>
    </tr>
  );
};

export default UserTableItem;
