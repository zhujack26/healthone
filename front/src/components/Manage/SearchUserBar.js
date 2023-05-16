/* import react */
import { useState, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { setSearchUserInfoList } from "../../redux/userList";
import { setSearchReportInfoList } from "../../redux/reportList";

/* import component */
import { Button } from "@material-ui/core";

/* import css */
import "../../assets/css/SearchUserBar.css";

/**
 *
 * @param {tableIndex} 검색 테이블 인덱스
 * @returns
 */
const SearchUserBar = ({ tableIndex }) => {
  // redux
  const dispatch = useDispatch();
  const userList = useSelector((state) => state.userList.userInfoList);
  const reportList = useSelector((state) => state.reportList.reportInfoList);

  const [searchKeyword, setSearchKeyword] = useState("");
  const searchRef = useRef();

  const doSearch = (tableIndex, keyword = searchKeyword) => {
    // alert(keyword);

    switch (tableIndex) {
      case 0:
        let searchUserList = userList.filter((userInfoData) => userInfoData.userEmail.includes(keyword));
        dispatch(setSearchUserInfoList(searchUserList));
        // console.dir(searchUserList);

        break;
      case 1:
        let searchReportList = reportList.filter((reportInfoData) => reportInfoData.email.includes(keyword));
        dispatch(setSearchReportInfoList(searchReportList));
        // console.dir(searchReportList);

        break;
      default:
        break;
    }
  };

  let rebounce = null;
  const onChangeHandle = (e) => {
    clearTimeout(rebounce);
    rebounce = setTimeout(() => {
      let keyword = e.target.value;
      setSearchKeyword(keyword);
      console.log(keyword);
      doSearch(tableIndex, keyword);
    }, 300);
  };

  return (
    <div className="search-user-bar">
      <div className="search-user-bar-frame">
        <p className="search-user-title">이메일 검색</p>
        <input className="user-search-input" ref={searchRef} onChange={onChangeHandle} />
        <Button
          className="search-btn"
          onClick={(e) => {
            doSearch(tableIndex);
          }}
        >
          검색
        </Button>
      </div>
    </div>
  );
};

export default SearchUserBar;
