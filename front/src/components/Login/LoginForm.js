/* react */

import { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";

/* components */
import { Button } from "@material-ui/core";

/* css */
import "../../assets/css/LoginForm.css";

// 로그인 정보를 담을 객체
class LoginInfo {
  constructor(id, pwd) {
    this.id = id;
    this.pwd = pwd;
  }
}

const LoginForm = () => {
  const navigate = useNavigate();

  // login-form ref
  const adminIdRef = useRef();
  const adminPwdRef = useRef();

  
  // login 정보를 담을 객체 state
  const [loginInfo, setLoginInfo] = useState(new LoginInfo("", ""));

  // 로그인 감정 함수
  const verifyAdmin = (id, pwd) => {
    if (id === "admin" && pwd === "admin1234") {
      navigate("/manage");
    } else {
      showWrongInfo();
    }
  };

  // 비밀번호 키다운 함수
  const watchKeyDown = (e) => {
    // adminIdRef.current.value === "admin" && adminPwdRef.current.value === "admin1234"
    let id = adminIdRef.current.value;
    let pwd = adminPwdRef.current.value;
    if (e.code === "Enter") {
      const inputLoginInfo = new LoginInfo(id, pwd);
      setLoginInfo(inputLoginInfo);

      if (inputLoginInfo.id === "admin" && inputLoginInfo.pwd === "admin1234") {
        navigate("/manage");
        localStorage.setItem("isLogin", true);
      } else {
        showWrongInfo();
      }
    }
  };

  const showWrongInfo = () => {
    alert("관리자 번호 또는 비밀번호를다시 확인해주세요");
  };

  return (
    <div className="login-form">
      <p className="admin-login-title">헬스원(HEALTHONE)</p>
      <div className="admin-id-label">
        <p className="id-title">관리자 등록 번호</p>
        <input className="admin-id-input" ref={adminIdRef} />
      </div>
      <div className="admin-pwd-label">
        <p className="pwd-title">비밀번호</p>
        <input
          className="admin-pwd-input"
          type="password"
          ref={adminPwdRef}
          onKeyDown={(e) => {
            watchKeyDown(e);
          }}
        />
      </div>
      <div className="login-btn-frame">
        <Button
          className="admin-login-btn"
          onClick={(e) => {
            verifyAdmin(adminIdRef.current.value, adminPwdRef.current.value);
          }}
        >
          로그인
        </Button>
      </div>
    </div>
  );
};

export default LoginForm;
