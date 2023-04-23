/* react */
import { useState, useEffect } from "react";

/* data class */
// import UserInfo from "../../data/UserInfo.js";

/* import component */
import UserTableItem from "./UserTableItem";

// import server apis //
import { getUerList } from "../../api/UserInfoAPI";

/* import css */
import "../../assets/css/UserInfoTable.css";
import "../../assets/css/UserTableItem.css";

/**
 *
 * @param {Arrays} tableColumnName 테이블 칼럼 명
 * @returns
 */
const UserInfoTable = ({ tableColumnName }) => {
  const [userInfoItem, setUserIntoItem] = useState([]);

  useEffect(() => {
    getUerList().then((res) => {
      // console.dir(res);
      setUserIntoItem(res.map((userInfoData, i) => <UserTableItem key={i} UserInfo={userInfoData} />));
    });
    // console.dir(userList);
  }, []);
  return (
    <div className="user-info-table">
      <table className="user-table">
        <thead>
          <tr>
            {tableColumnName.map((e, i) => (
              <th key={i}>{e}</th>
            ))}
          </tr>
        </thead>
        <tbody>{userInfoItem}</tbody>
      </table>
    </div>
  );
};

export default UserInfoTable;
