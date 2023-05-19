// 에러 메세지 상수 import
import { ERR_USER_LIST } from "../constants/ErrorMessage.js";

// 통신을 위한 data class import
import UserInfo from "../data/UserInfo.js";

import axios from "axios";
/**
 * @author 1-hee
 * @copyright 2023
 */

// 엑시오스 기본 세팅
axios.defaults.baseURL = "https://jsonplaceholder.typicode.com";
axios.defaults.withCredentials = true;

/////////* GET *///////////////////

/**
 * @returns {Array<UserInfo>} 사용자 정보 리스트
 */
const getUerList = async () => {
  const url = "/users";
  let userList = [];

  await axios
    .get(url)
    .then((res) => {
      if (res.status === 200) {
        let dataList = res.data;
        let tempDate = new Date();
        let tempDateString = `${tempDate.getFullYear()}-${
          tempDate.getMonth() + 1 < 9 ? `0${tempDate.getMonth() + 1}` : tempDate.getMonth() + 1
        }-${tempDate.getDate()}`;

        dataList.forEach((data, i) => {
          let userInfo = new UserInfo(data.name, data.email, tempDateString, "활성");
          userList.push(userInfo);
        });
      }
    })
    .catch((err) => {
      console.log(ERR_USER_LIST);
    });

  return userList;
};

export { getUerList };
