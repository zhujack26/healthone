/* import react */
import { useSelector } from "react-redux";

/* import component */
import { Button } from "@material-ui/core";

/* import css */
import "../../assets/css/ReportAnswerModal.css";

/**
 *
 * @param {Object} Report 불편사항 객체(title, name, email, date, content) 포함.
 * @param {Object} closeModal 모달 닫는 함수
 * @returns
 */
const ReportAnswerModal = ({ ReportInfo, closeModal }) => {
  return (
    <div className="modal-container">
      <div className="background"></div>
      <div className="report-answer-modal">
        <div className="title-div">
          <h3>제목</h3>
          <p>{ReportInfo.title}</p>
        </div>
        <div className="email-div">
          <h3>이메일</h3>
          <p>{ReportInfo.email}</p>
        </div>
        <div className="date-div">
          <h3>등록 날짜</h3>
          <p>{ReportInfo.regDate}</p>
        </div>
        <fieldset className="content-div">
          <legend>문의 내용</legend>
          <textarea disabled={true} value={ReportInfo.reportContent}></textarea>
        </fieldset>

        <fieldset className="answer-div">
          <legend>답변 입력</legend>
          <textarea defaultValue="" placeholder="답변을 입력해주세요."></textarea>
        </fieldset>

        <div className="btn-frame">
          <Button
            className="answer-btn"
            onClick={(e) => {
              closeModal();
            }}
          >
            답변 등록
          </Button>
          <Button
            className="cancel-btn"
            onClick={(e) => {
              closeModal();
            }}
          >
            취소
          </Button>
        </div>
      </div>
    </div>
  );
};

export default ReportAnswerModal;
