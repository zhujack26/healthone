// TODO
// 1. Transition.js로 페이지네이션 구현
// 2. Reduxtookit 적용하여 상태관리

/* import react*/
import { useState } from "react";


/* import component */
import ManagePageHeader from "../components/Manage/ManagePageHeader";
import SearchUserBar from "../components/Manage/SearchUserBar";
import ManageSortFilterOptionBar from "../components/Manage/ManageSortFilterOptionBar";
import UserInfoTable from "../components/UserManage/UserInfoTable";
import ReportManageTable from "../components/ReportManage/ReportManageTable"
import ReportAnswerModal from "../components/ReportManage/ReportAnswerModal"

/* import css */
import "../assets/css/ManagePage.css";

class Report {
  constructor(title, name, email, date, content) {
    this.title = title;
    this.name = name;
    this.email = email;
    this.date = date;
    this.content = content;
  }
}

const ManagePage = () => {
  const sortOptions = ["선택안함", "이메일", "이름", "이메일"];
  const filterOptions1 = ["전체", "활성", "휴면", "정지"];
  const filterOptions2 = ["전체", "처리완료", "처리접수", "등록완료"];

  const [isShowModal, setIsShowMdoal] = useState(false);
  
  
  const report = new Report(
    "로그인이 안돼요", "홍길동", "hong123@gmail.com", "2023.04.23.", `지금 앱 내에서 로그인 하는데,
    구글 계정 로그인 했음에도
    로그인이 자꾸 안됩니다...
    
    다시 재설치해서 해봐도
    같은 증상 반복되는데
    이거 어떻게 해야하나요
    
    지금 앱 이용하려고 하는데
    사용조차 안되니
    정말 답답하네요 
    
    빠른 처리 부탁드립니다`
  )

  return (
    <>
      <div className="manage-page">
        <ManagePageHeader pageTitle={"사용자 관리"} />
        <SearchUserBar />
        <ManageSortFilterOptionBar isSort={false} filterOptions={filterOptions2} />
        {/* <UserInfoTable /> */}
        <ReportManageTable showAnswerModal={()=>{setIsShowMdoal(true)}} />
        {/* <ReportAnswerModal Report={report} />         */}
        {isShowModal?<ReportAnswerModal Report={report} closeModal={()=>{setIsShowMdoal(false)}} />:<></>}
      </div>
    </>
  );
};

export default ManagePage;
