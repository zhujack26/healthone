// 에러 메세지 상수 import
import { ERR_REPORT_LIST } from "../constants/ErrorMessage.js";

// 통신을 위한 data class import
import ReportInfo from "../data/ReportInfo.js";

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
 * @returns {Array<ReportInfo>} 불편사항 정보 리스트
 */
const getReportList = async () => {
  const url = "/comments";
  let reportList = [];

  await axios
    .get(url)
    .then((res) => {
      if (res.status === 200) {
        let tempDate = new Date();
        let tempDateString = `${tempDate.getFullYear()}-${
          tempDate.getMonth() + 1 < 9 ? `0${tempDate.getMonth() + 1}` : tempDate.getMonth() + 1
        }-${tempDate.getDate()}`;

        let tempList = res.data.filter((_, index) => index < 20);
        tempList.map((data, i) => {
          let reportInfo = new ReportInfo(data.id, data.email, data.name, tempDateString, data.body);
          reportList.push(reportInfo);
        });
      }
    })
    .catch((err) => {
      console.log(ERR_REPORT_LIST);
    });

  return reportList;
};

export { getReportList };
