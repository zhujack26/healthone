/* react */
import { useState, useEffect, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setUserFilterStatus, setUserFilterOption, setSortUserInfoList } from "../../redux/userList";
import { setReportFilterStatus, setReportFilterOption, setSortReportInfoList } from "../../redux/reportList";

/* import component */

/* import css */
import "../../assets/css/ManageSortFilterOptionBar.css";

/**
 *
 * @param {number} tableIndex 테이블 구분을 위한 숫자형 index {0:user, 1:report}
 * @param {Boolean} isSort 정렬 옵션 표시 여부
 * @param {Array} sortOptions 정렬 옵션 문자열 배열
 * @param {Boolean} isFilter 필터 옵션 표시 여부
 * @param {Array} filterOptions 필터 옵션 문자열 배열
 *
 * @returns
 */
const ManageSortFilterOptionBar = ({
  tableIndex = 0,
  isSort = true,
  sortOptions = ["선택안함"],
  isFilter = true,
  filterOptions = ["전체"],
}) => {
  // redux
  const dispatch = useDispatch();
  // 사용자 정보 redux
  const userList = useSelector((state) => state.userList.userInfoList);
  const searchUserInfoList = useSelector((state) => state.userList.searchUserInfoList);
  const sortUserInfoList = useSelector((state) => state.userList.sortUserInfoList); // searchList가 < userList개 미만이다 =  검색했다.

  // 불편사항 정보 redux;
  const reportList = useSelector((state) => state.reportList.reportInfoList);
  const searchReportInfoList = useSelector((state) => state.reportList.searchReportInfoList);
  const sortReportInfoList = useSelector((state) => state.reportList.sortReportInfoList); // searchList가 < reportList개 미만이다 =  검색했다.

  const filterRef = useRef();
  const sortRef = useRef();
  // arr.sort((a, b) => a.name.localeCompare(b.name));
  // "이메일", "이름", "가입일자"

  const onChangeHandle = (e) => {
    // 조건식, 사람인지 레포트인지 구분 필요
    let index = e.target.selectedIndex;
    if (tableIndex === 0) {
      dispatch(setUserFilterOption(filterOptions[index]));
      dispatch(setUserFilterStatus(index));
    } else if (tableIndex === 1) {
      dispatch(setReportFilterOption(filterOptions[index]));
      dispatch(setReportFilterStatus(index));
    }
  };

  const onSortHandle = (e) => {
    if (userList.length > 0) { //검색했다!
      if (searchUserInfoList.length > 0) {
        // 검색했다.
        let sortUserList = [...searchUserInfoList];
        sortUserList.sort((a, b) => a.userEmail.localeCompare(b.userEmail));
        // console.dir(searchUserInfoList);
        // console.dir(sortUserList);
        dispatch(setSortUserInfoList(sortUserList));
      } else {
        let sortUserList = [...userList];
        sortUserList.sort((a, b) => a.userEmail.localeCompare(b.userEmail));
        // console.dir(userList);
        // console.dir(sortUserList);
        dispatch(setSortUserInfoList(sortUserList));
      }
    }
  };

  return (
    <div className="manage-sort-filter-option-bar">
      <div className="option-div-frame">
        {isSort ? (
          <div className="sort-option-div">
            <p>정렬 기준</p>
            <select className="sort-role-select" ref={sortRef} onChange={onSortHandle}>
              {sortOptions.map((e, i) => (
                <option key={i}>{e}</option>
              ))}
            </select>
          </div>
        ) : (
          <></>
        )}
        {isFilter ? (
          <div className="state-filter-div">
            <p>상태 필터</p>
            <select className="user-state-select" ref={filterRef} onChange={onChangeHandle}>
              {filterOptions.map((e, i) => (
                <option key={i}>{e}</option>
              ))}
            </select>
          </div>
        ) : (
          <></>
        )}
      </div>
    </div>
  );
};

export default ManageSortFilterOptionBar;
