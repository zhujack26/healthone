/* import component */

/* import css */
import "../../assets/css/ManageSortFilterOptionBar.css";

/**
 *
 * @param {Boolean} isSort 정렬 옵션 표시 여부
 * @param {Array} sortOptions 정렬 옵션 문자열 배열
 * @param {Boolean} isFilter 필터 옵션 표시 여부
 * @param {Array} filterOptions 필터 옵션 문자열 배열
 *
 * @returns
 */
const ManageSortFilterOptionBar = ({
  isSort = true,
  sortOptions = ["선택안함"],
  isFilter = true,
  filterOptions = ["전체"],
}) => {
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
            <select className="user-state-select">
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
