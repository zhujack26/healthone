/* components */
import { Button } from "@material-ui/core";

/* css */
import "../../assets/css/LoginForm.css";

const LoginForm = () => {
  return (
    <div className="login-form">
      <p className="admin-login-title">헬스원(HEALTHONE)</p>
      <div className="admin-id-label">
        <p className="id-title">관리자 등록 번호</p>
        <input className="admin-id-input" />
      </div>
      <div className="admin-pwd-label">
        <p className="pwd-title">비밀번호</p>
        <input className="admin-pwd-input" />
      </div>
      <div className="login-btn-frame">
        <Button className="admin-login-btn">로그인</Button>
      </div>
    </div>
  );
};

export default LoginForm;
