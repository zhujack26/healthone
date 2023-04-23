/* react */
import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setReportInfoList } from "../../redux/reportList";

/* data class */

/* import component */
import ReportTableItem from "./ReportTableItem";
import ReportAnswerModal from "./ReportAnswerModal";

// import server apis //
import { getReportList } from "../../api/ReportInfoAPI";

/* import css */
import "../../assets/css/ReportManageTable.css";
import "../../assets/css/ReportTableItem.css";

/**
 *
 * @param {Arrays} tableColumnName 테이블 칼럼 명
 * @returns
 */
const ReportManageTable = ({ showAnswerModal, tableColumnName }) => {
  const dispatch = useDispatch();
  const reportList = useSelector((state) => state.reportList.reportInfoList);
  const curRepoInfo = useSelector((state) => state.reportList.currentReportInfo);

  const [reportTableItem, setReportTableItem] = useState([]);
  const [isShowModal, setIsShowModal] = useState(false);

  useEffect(() => {
    getReportList().then((res) => {
      let reportInfoList = [];
      res.map((reportInfoData, i) => {
        reportInfoList.push(reportInfoData);
      });
      dispatch(setReportInfoList(reportInfoList));
    });

    if (reportList.length > 0) {
      setReportTableItem(
        reportList.map((reportInfoData, i) => (
          <ReportTableItem
            ReportInfo={reportInfoData}
            showAnswerModal={() => {
              setIsShowModal(true);
            }}
            key={i}
          />
        ))
      );
    }
  }, [reportList.length]);

  return (
    <div className="report-manage-table">
      <table className="report-table">
        <thead>
          <tr>
            {tableColumnName.map((e, i) => (
              <th key={i}>{e}</th>
            ))}
          </tr>
        </thead>
        <tbody>{reportTableItem}</tbody>
      </table>
      {isShowModal ? (
        <ReportAnswerModal
          ReportInfo={curRepoInfo}
          closeModal={() => {
            setIsShowModal(false);
          }}
        />
      ) : (
        <></>
      )}
    </div>
  );
};

export default ReportManageTable;
