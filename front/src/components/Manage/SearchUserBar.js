/* import component */

/* import css */
import "../../assets/css/SearchUserBar.css";

const SearchUserBar = () => {
  return (
    <div className="search-user-bar">
      <p className="search-user-title">이메일 검색</p>
      <input className="user-search-input" />
      <p className="search-btn">검색</p>
    </div>
  );
};

export default SearchUserBar;
