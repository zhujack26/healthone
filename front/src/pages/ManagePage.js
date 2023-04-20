/* import react*/

/* import component */
import ManagePageHeader from "../components/Manage/ManagePageHeader";
import SearchUserBar from "../components/Manage/SearchUserBar";
import ManageSortFilterOptionBar from "../components/Manage/ManageSortFilterOptionBar";
import UserInfoTable from "../components/UserManage/UserInfoTable";
import ReportManageTable from "../components/ReportManage/ReportManageTable"

/* import css */
import "../assets/css/ManagePage.css";

const ManagePage = () => {
  const sortOptions = ["선택안함", "이메일", "이름", "이메일"];
  const filterOptions1 = ["전체", "활성", "휴면", "정지"];
  const filterOptions2 = ["전체", "처리완료", "처리접수", "등록완료"];
  

  return (
    <>
      <div className="manage-page">
        <ManagePageHeader pageTitle={"사용자 관리"} />
        <SearchUserBar />
        <ManageSortFilterOptionBar isSort={false} filterOptions={filterOptions2} />
        {/* 여기만 다름! */}
        {/* <UserInfoTable /> */}
        <ReportManageTable/>
      </div>
    </>
  );
};

export default ManagePage;
