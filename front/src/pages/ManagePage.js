/* import component */
import ManagePageHeader from "../components/Manage/ManagePageHeader";
import SearchUserBar from "../components/Manage/SearchUserBar";
import ManageSortFilterOptionBar from "../components/Manage/ManageSortFilterOptionBar";
import UserInfoTable from "../components/UserManage/UserInfoTable";
import ReportManageTable from "../components/ReportManage/ReportManageTable"

/* import css */
import "../assets/css/ManagePage.css";

const ManagePage = () => {
  return (
    <>
      <div className="manage-page">
        <ManagePageHeader />
        <SearchUserBar />
        <ManageSortFilterOptionBar />
        {/* 여기만 다름! */}
        {/* <UserInfoTable /> */}
        <ReportManageTable/>
      </div>
    </>
  );
};

export default ManagePage;
