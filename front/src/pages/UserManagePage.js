/* import component */
import ManagePageHeader from "../components/UserManage/ManagePageHeader";
import SearchUserBar from "../components/UserManage/SearchUserBar";
import ManageSortFilterOptionBar from "../components/UserManage/ManageSortFilterOptionBar";
import UserInfoTable from "../components/UserManage/UserInfoTable";

/* import css */
import "../assets/css/UserManagePage.css";

const UserManagePage = () => {
  return (
    <>
      <div className="user-manage-page">
        <ManagePageHeader />
        <SearchUserBar />
        <ManageSortFilterOptionBar />
        <UserInfoTable />
      </div>
    </>
  );
};

export default UserManagePage;
