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
const ManagePageHeader = ({ pageTitle, doLogout, setTable}) => {
  const [topNavText, setTopNavNext] = useState(["회원 관리", "불편 사항"]);
  const [navTextState, setNavTextState] = useState(true);

  // 테이블 전환 메서드
  const changeTable = () => {
    setNavTextState(!navTextState);
    if (!navTextState) {
      setTable(true);
    }else setTable(false);
  }


  return (
    <>
      <div className="manage-page-header">
        <p className="page-title">{pageTitle}</p>
        <div className="right-btn">
          <div className="nav-btns">
            <p
              className={navTextState ? "nav-btn-active" : ""}
              onClick={(e)=>{changeTable()}}>
              {topNavText[0]}
            </p>
            <p
              className={!navTextState ? "nav-btn-active" : ""}
              onClick={(e)=>{changeTable()}}
            >
              {topNavText[1]}
            </p>
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
