/* react */
import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setUserInfoList } from "../../redux/userList";

/* data class */

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
  const dispatch = useDispatch();
  const userList = useSelector((state) => state.userList.userInfoList);

  const [userInfoItem, setUserIntoItem] = useState([]);

  useEffect(() => {
    getUerList().then((res) => {
      // console.dir(res);
      let userInfoList = [];
      res.map((userInfoData, i) => {
        userInfoList.push(userInfoData);
      });
      dispatch(setUserInfoList(userInfoList));
    });
    if (userList.length > 0) {
      setUserIntoItem(userList.map((userInfoData, i) => <UserTableItem key={i} UserInfo={userInfoData} />));
    }
  }, [userList.length]);

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
