/* import component */
import UserTableItem from "./UserTableItem";

/* import css */
import "../../assets/css/UserInfoTable.css";
import "../../assets/css/UserTableItem.css"
/* import .. */
import { useState, useEffect } from "react";
import { Button } from "@material-ui/core";

const UserInfoTable = () => {

  const size = 20;

  
  const [userInfoItem, setUserIntoItem] = useState([Array(size).fill().map((e, i)=> <UserTableItem key={i}/>)]);

  useEffect(() => {}, []);
  return (
    <div className="user-info-table">
      <table className="user-table">
        <thead>
          <tr>
            <th>이름</th>
            <th>아이디</th>
            <th>이메일</th>
            <th>가입 일자</th>
            <th>상태</th>
            <th>관리</th>
          </tr>
        </thead>
        <tbody>
          {userInfoItem}          
        </tbody>
      </table>
    </div>
  );
};

export default UserInfoTable;
