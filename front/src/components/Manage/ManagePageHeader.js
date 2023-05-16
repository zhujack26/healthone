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
const ManagePageHeader = ({ pageTitle, doLogout, setTableIndex, tableIndex }) => {
  const [topNavText] = useState(["회원 관리", "불편 사항"]);
  const [curNavIdx, setCurNavIdx] = useState(tableIndex);

  // 테이블 전환 메서드
  const changeTableIndex = (e) => {
    let index = topNavText.indexOf(e.target.innerText);
    index = index < 0 ? 0 : index; // -1 처리
    setTableIndex(index);
    setCurNavIdx(index);
  };

  // 네비게이션 따라 클래스 속성 주기 위한 함수
  const isCurrentNav = (navIdx) => {
    return navIdx === curNavIdx;
  };

  return (
    <>
      <div className="manage-page-header">
        <p className="page-title">{pageTitle}</p>
        <div className="right-btn">
          <div className="nav-btns">
            <p className={isCurrentNav(0) ? "nav-btn-active" : ""} onClick={changeTableIndex}>
              {topNavText[0]}
            </p>
            <p className={isCurrentNav(1) ? "nav-btn-active" : ""} onClick={changeTableIndex}>
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
