/* import component */
import UserTableHead from "./UserTableHead";
import UserTableItem from "./UserTableItem";

/* import css */
import "../../assets/css/UserInfoTable.css";

/* import .. */
import { useState, useEffect } from "react";

const UserInfoTable = () => {
  let size = 30;
  const [itemList, setItemList] = useState([
    Array(size)
      .fill()
      .map((e, idx) => <UserTableItem key={idx} />),
  ]);
  useEffect(() => {}, []);
  return (
    <div className="user-info-table">
      <UserTableHead />
      <div className="user-info-item-list">{itemList}</div>
    </div>
  );
};

export default UserInfoTable;
