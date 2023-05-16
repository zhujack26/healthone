// TODO
// 2. 정렬 기준 선택 정렬 기준 대로 정보 테이블 정보 재 렌더링
// 3. 답변등록하면 처리상태 >> 처리 완료로 바꾸기
// 4. 사용자 정보 수정 저장 시 redux userInfoList에 해당 정보만 교체해서 갈아 끼우기

/* import react*/
import { useState } from "react";
import { useNavigate } from "react-router-dom";

/* import component */
import ManagePageHeader from "../components/Manage/ManagePageHeader";
import SearchUserBar from "../components/Manage/SearchUserBar";
import ManageSortFilterOptionBar from "../components/Manage/ManageSortFilterOptionBar";
import UserInfoTable from "../components/UserManage/UserInfoTable";
import ReportManageTable from "../components/ReportManage/ReportManageTable";

/* data class */
import UserInfo from "../data/UserInfo";
import ReportInfo from "../data/ReportInfo";

// import modules //

/* import css */
import "../assets/css/ManagePage.css";
import { useEffect } from "react";

// Report 내용을 담을 class

const ManagePage = () => {
  const pageTitle = ["사용자 관리", "불편 사항 관리"];
  const sortOptions1 = ["선택안함", "이메일", "이름", "가입일자"];
  const filterOptions1 = ["전체", "활성", "휴면", "정지"];
  const filterOptions2 = ["전체", "등록완료", "처리접수", "처리완료"];
  const userInfoHeaders = ["이름", "이메일", "가입 일자", "상태", "관리"];
  const reportInfoHeaders = ["CODE", "이메일", "제목", "등록 날짜", "문의 유형", "처리 상태", "관리"];

  // // redux
  // const userList = useSelector((state) => state.userList.userInfoList);
  // const reportList = useSelector((state) => state.reportList.reportList.reportInfoList);

  const navigate = useNavigate();

  // 테이블 옵션 불러오기 위한 함수
  const getTableInfo = (tableIndex) => {
    switch (tableIndex) {
      case 0:
        return {
          isSort: true,
          sortOptions: sortOptions1,
          filterOptions: filterOptions1,
        };
      case 1:
        return {
          isSort: false,
          sortOptions: [],
          filterOptions: filterOptions2,
        };
      default:
        return {
          isSort: false,
          sortOptions: [],
          filterOptions: [],
        };
    }
  };

  const getTable = (tableIndex) => {
    // 테이블들
    const userInfoTable = <UserInfoTable tableColumnName={userInfoHeaders} />;
    const reportManageTable = <ReportManageTable tableColumnName={reportInfoHeaders} />;

    switch (tableIndex) {
      case 0:
        return userInfoTable;
      case 1:
        return reportManageTable;
      default:
        return <></>;
    }
  };

  const [tableIndex, setTableIndex] = useState(0);
  const [tableInfo, setTableInfo] = useState(getTableInfo(0));

  useEffect(() => {}, [tableInfo]);

  return (
    <>
      <div className="manage-page">
        <ManagePageHeader
          pageTitle={pageTitle[tableIndex]}
          doLogout={() => {
            localStorage.removeItem("isLogin");
            navigate("/login");
          }}
          setTableIndex={(e) => {
            setTableIndex(e);
            setTableInfo(e);
          }}
          tableIndex={tableIndex}
        />
        <SearchUserBar tableIndex={tableIndex} />
        <ManageSortFilterOptionBar
          tableIndex={tableIndex}
          isSort={getTableInfo(tableIndex).isSort}
          sortOptions={getTableInfo(tableIndex).sortOptions}
          filterOptions={getTableInfo(tableIndex).filterOptions}
        />
        {/* <UserInfoTable /> */}
        {getTable(tableIndex)}
      </div>
    </>
  );
};

export default ManagePage;
