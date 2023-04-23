// TODO
// 1. 검색창 검색 시 서버와 디바운스 기법 적용하여 정보 로딩
// 2. 정렬 기준 선택 정렬 기준 대로 정보 테이블 정보 재 렌더링
// 3. 답변등록하면 처리상태 >> 처리 완료로 바꾸기

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

// import modules //

/* import css */
import "../assets/css/ManagePage.css";

// Report 내용을 담을 class

const ManagePage = () => {
  const pageTitle = ["사용자 관리", "불편 사항 관리"];
  const sortOptions = ["선택안함", "이메일", "이름", "이메일"];
  const filterOptions1 = ["전체", "활성", "휴면", "정지"];
  const filterOptions2 = ["전체", "처리완료", "처리접수", "등록완료"];
  const userInfoHeaders = ["이름", "이메일", "가입 일자", "상태", "관리"];
  const reportInfoHeaders = ["CODE", "이메일", "제목", "등록 날짜", "문의 유형", "처리 상태", "관리"];

  const navigate = useNavigate();

  // 테이블 교체 함수
  const [tableState, setTableState] = useState(true);

  // 테이블들
  const useInfoTable = <UserInfoTable tableColumnName={userInfoHeaders} />;
  const reportTable = <ReportManageTable tableColumnName={reportInfoHeaders} />;

  return (
    <>
      <div className="manage-page">
        <ManagePageHeader
          pageTitle={tableState ? pageTitle[0] : pageTitle[1]}
          doLogout={() => {
            localStorage.removeItem("isLogin");
            navigate("/login");
          }}
          setTable={(e) => setTableState(e)}
        />
        <SearchUserBar />
        <ManageSortFilterOptionBar
          tableIndex={tableState ? 0 : 1}
          isSort={tableState}
          sortOptions={sortOptions}
          filterOptions={tableState ? filterOptions1 : filterOptions2}
        />
        {/* <UserInfoTable /> */}
        {tableState ? useInfoTable : reportTable}
      </div>
    </>
  );
};

export default ManagePage;
