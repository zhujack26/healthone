/* react */
import { useState, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setUserFilterStatus, setUserFilterOption } from "../../redux/userList";
import { setReportFilterStatus, setReportFilterOption } from "../../redux/reportList";

/* import component */

/* import css */
import "../../assets/css/ManageSortFilterOptionBar.css";
import { useEffect } from "react";

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
  const dispatch = useDispatch();
  // 버튼
  // const userFilterStatus = useSelector((state) => state.userList.userFilterStatus);
  // const userFilterOption = useSelector((state) => state.userList.filterOption);

  // const [filterIndex, setFilterIndex] = useState(0);

  const filterRef = useRef();
  const sortRef = useRef();

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

  return (
    <div className="manage-sort-filter-option-bar">
      <div className="option-div-frame">
        {isSort ? (
          <div className="sort-option-div">
            <p>정렬 기준</p>
            <select className="sort-role-select">
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
