/* import react */
import { useState } from "react";

/* import component */
import { Button } from "@material-ui/core";

/* import css */
import "../../assets/css/ManagePageHeader.css";

/**
 * @param {String} pageTitle 페이지 제목
 * @param {function} doLogout 로그아웃 함수
 * @returns
 */
const ManagePageHeader = ({ pageTitle, doLogout }) => {
  const [topNavText, setTopNavNext] = useState(["회원 관리", "불편 사항"]);

  return (
    <>
      <div className="manage-page-header">
        <p className="page-title">{pageTitle}</p>
        <div className="right-btn">
          <div className="nav-btns">
            <p>{topNavText[0]}</p>
            <p>{topNavText[1]}</p>
          </div>
          <Button
            className="logout-btn"
            onClick={(e) => {
              doLogout();
            }}
          >
            로그아웃
          </Button>
        </div>
      </div>
    </>
  );
};

export default ManagePageHeader;
