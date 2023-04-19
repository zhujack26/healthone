/* import component */

/* import css */

import "../../assets/css/UserTableHead.css";
const UserTableHead = () => {
  return (
    <div className="user-table-head">
      <div className="name-div">이름</div>
      <div className="id-div">아이디</div>
      <div className="email-div">이메일</div>
      <div className="date-div">가입일</div>
      <div className="state-div">상태</div>
      <div className="controll-div">관리</div>
    </div>
  );
};

export default UserTableHead;
