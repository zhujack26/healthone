import { BrowserRouter as Router, Routes, Route, useLocation, Redirect, Navigate } from "react-router-dom";
import { TransitionGroup, CSSTransition } from "react-transition-group";

// Pages
import LoginPage from "./pages/LoginPage";
import ManagePage from "./pages/ManagePage";

// import "./Transition.css";

const Transition = () => {
  const location = useLocation("/login");
  return (
    <div>
      <TransitionGroup className="transitions-wrapper">
        <Routes location={location}>
          <Route path="/login" element={<LoginPage />}></Route>

          <Route
            path="/manage"
            element={localStorage.getItem('isLogin')?(
              <ManagePage/>
            ) : ( 
                <Navigate to="/login"/>
            )}
          />

          <Route path="/manage" element={<ManagePage />}></Route>

          <Route path="*" element={<Navigate to ="/login" />}></Route>
          {/*<Route path="*" element={<NotFoundPage />}></Route>*/}

          {/* <Route path="/product/*" element={<Product />}></Route> */}
          {/* 상단에 위치하는 라우트들의 규칙을 모두 확인, 일치하는 라우트가 없는경우 처리 */}
        </Routes>
        {/* </CSSTransition> */}
      </TransitionGroup>
    </div>
  );
};

export default Transition;
