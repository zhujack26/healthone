/* import component */
import LoginForm from "../components/Login/LoginForm";

/* import css */
import "../assets/css/LoginPage.css";

/**
 * @param {props} props
 * @returns  {void}
 */

const LoginPage = () => {
  return (
    <div className="login-page">
      <LoginForm />
    </div>
  );
};

export default LoginPage;
