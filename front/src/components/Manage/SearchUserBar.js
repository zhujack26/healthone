/* import component */
import { Button } from "@material-ui/core";

/* import css */
import "../../assets/css/SearchUserBar.css";

const SearchUserBar = () => {
  return (
    <div className="search-user-bar">
      <div className="search-user-bar-frame">
        <p className="search-user-title">이메일 검색</p>
        <input className="user-search-input" />
        <Button className="search-btn">검색</Button>
      </div>
    </div>
  );
};

export default SearchUserBar;
