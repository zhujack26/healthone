/* import component */

/* import css */
import "../../assets/css/ManageSortFilterOptionBar.css";

const ManageSortFilterOptionBar = () => {
  return (
    <div className="manage-sort-filter-option-bar">
      <div className="option-div-frame">
        <div className="sort-option-div">
          <p>정렬 기준</p>
          <select className="sort-role-select">선택안함</select>
        </div>
        <div className="state-filter-div">
          <p>상태 필터</p>
          <select className="user-state-select">선택안함</select>
        </div>
      </div>
    </div>
  );
};

export default ManageSortFilterOptionBar;
