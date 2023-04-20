/* import component */

/* import css */
import "../../assets/css/ManageSortFilterOptionBar.css";

const ManageSortFilterOptionBar = () => {
  const sortOptions = ["선택안함", "아이디", "이름", "이메일"];
  const filterOptions = ["전체", "처리완료", "처리접수", "등록완료"];


  return (
    <div className="manage-sort-filter-option-bar">
      <div className="option-div-frame">
        <div className="sort-option-div">
          <p>정렬 기준</p>
          <select className="sort-role-select">
            {
              sortOptions.map((e, i) => <option>{e}</option>)
            }
          </select>
        </div>
        <div className="state-filter-div">
          <p>상태 필터</p>
          <select className="user-state-select">
            {
              filterOptions.map((e, i) => <option>{e}</option>)
            }
          </select>
        </div>
      </div>
    </div>
  );
};

export default ManageSortFilterOptionBar;
