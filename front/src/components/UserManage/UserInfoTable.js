/* react */
import { useState, useEffect } from "react";
import { Button } from "@material-ui/core";

/* import component */
import UserTableItem from "./UserTableItem";

/* import css */
import "../../assets/css/UserInfoTable.css";
import "../../assets/css/UserTableItem.css"

// UserInfoData 
class UserInfoData {
  constructor(name, id, email, registDate, userState) {
    this.name = name;
    this.id = id;
    this.email = email;
    this.registDate = registDate;
    this.userState = userState;    
  }

  // "이름", "아이디", "이메일", "가입 일자", "상태", "관리"
} 
      {/* <td></td>
      <td>hong123</td>
      <td>hong123@gmail.com</td>
      <td>2023. 04. 19.</td>
      <td>활성</td> */}

/**
 * 
 * @param {Arrays} tableColumnName 테이블 칼럼 명 
 * @returns 
 */
const UserInfoTable = ({ tableColumnName }) => {
  
  const userInfoData = new UserInfoData("홍길동", "hong123", "hong123@gmail.com", "2023. 04. 19.", "활성")

  const size = 20;  
  const [userInfoItem, setUserIntoItem] =
    useState([
      Array(size)
        .fill()
        .map((e, i) =>
          <UserTableItem key={i} UserInfoData={userInfoData} />)
    ]);

  useEffect(() => {}, []);
  return (
    <div className="user-info-table">
      <table className="user-table">
        <thead>
          <tr>
            {tableColumnName.map((e, i) => <th>{e}</th>)}
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
