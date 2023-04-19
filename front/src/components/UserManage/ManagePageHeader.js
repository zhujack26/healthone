/* import component */


/* import css */
import "../../assets/css/ManagePageHeader.css"

const ManagePageHeader = () => {
    return (
        <>
            <div className="manage-page-header">
                <p className="page-title">사용자 관리</p>
                <div className="right-btn">
                    <div className="nav-btns">
                        <p>회원 관리</p>
                        <p>불편 사항</p>
                    </div>
                    <buttton className="logout-btn">
                        로그아웃
                    </buttton>                    
                </div>
            </div>
        
        </>
    )
}

export default ManagePageHeader;