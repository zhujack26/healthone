package com.secui.healthone.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun PolicyDialog(onConfirm: () -> Unit) {
    Dialog(onDismissRequest = onConfirm) {
        Box(modifier = Modifier
            .heightIn(max = 500.dp)
            .background(Color.White)
            .padding(16.dp)
        ) {
            Column {
                Text("개인정보 처리방침", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                LazyColumn {
                    items(
                        items = listOf(
                        " 개인정보처리방침 \n" +
                                "제1조(목적) \n" +
                                "헬스원(healthone) (이하 '회사'라고 함)는 회사가 제공하고자 하는 서비스(이하 '회사 서비스')를 이용 \n" +
                                "하는 개인(이하 '이용자' 또는 '개인')의 정보(이하 '개인정보')를 보호하기 위해, 개인정보보호법, 정보 \n" +
                                "통신망 이용촉진 및 정보보호 등에 관한 법률(이하 '정보통신망법') 등 관련 법령을 준수하고, 서비스 이 \n" +
                                "용자의 개인정보 보호 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개 \n" +
                                "인정보처리방침(이하 '본 방침')을 수립합니다. \n" +
                                "제2조(개인정보 처리의 원칙) \n" +
                                "개인정보 관련 법령 및 본 방침에 따라 회사는 이용자의 개인정보를 수집할 수 있으며 수집된 개인정보 \n" +
                                "는 개인의 동의가 있는 경우에 한해 제3자에게 제공될 수 있습니다. 단, 법령의 규정 등에 의해 적법하 \n" +
                                "게 강제되는 경우 회사는 수집한 이용자의 개인정보를 사전에 개인의 동의 없이 제3자에게 제공할 수도 \n" +
                                "있습니다. \n" +
                                "제3조(본 방침의 공개) \n" +
                                "1. 회사는 이용자가 언제든지 쉽게 본 방침을 확인할 수 있도록 회사 홈페이지 첫 화면 또는 첫 화면과의 연 \n" +
                                "결화면을 통해 본 방침을 공개하고 있습니다. \n" +
                                "2. 회사는 제1항에 따라 본 방침을 공개하는 경우 글자 크기, 색상 등을 활용하여 이용자가 본 방침을 쉽게 확 \n" +
                                "인할 수 있도록 합니다. \n" +
                                "제4조 (본 방침의 변경) \n" +
                                "1. 본 방침은 개인정보 관련 법령, 지침, 고시 또는 정부나 회사 서비스의 정책이나 내용의 변경에 따라 개정 \n" +
                                "될 수 있습니다. \n" +
                                "2. 회사는 제1항에 따라 본 방침을 개정하는 경우 다음 각호 하나 이상의 방법으로 공지합니다. \n" +
                                "가. 회사가 운영하는 인터넷 홈페이지의 첫 화면의 공지사항란 또는 별도의 창을 통하여 공지하는 방법 \n" +
                                "나. 서면·모사전송·전자우편 또는 이와 비슷한 방법으로 이용자에게 공지하는 방법 \n" +
                                "3. 회사는 제2항의 공지는 본 방침 개정의 시행일로부터 최소 7일 이전에 공지합니다. 다만, 이용자 권리의 \n" +
                                "중요한 변경이 있을 경우에는 최소 30일 전에 공지합니다. \n" +
                                "제5조(회원가입을 위한 정보) \n" +
                                "회사는 이용자의 회사 서비스에 대한 회원가입을 위하여 다음과 같은 정보를 수집합니다. \n" +
                                "1. 필수 수집 정보: 이메일 주소, 이름 및 닉네임 \n" +
                                "제6조(개인정보 수집 방법) \n" +
                                "회사는 다음과 같은 방법으로 이용자의 개인정보를 수집합니다. \n" +
                                "1. 어플리케이션 등 회사가 제공하는 홈페이지 외의 서비스를 통해 이용자가 자신의 개인정보를 입력하는 \n" +
                                "방식 \n" +
                                "제7조(개인정보의 이용) \n" +
                                "회사는 개인정보를 다음 각 호의 경우에 이용합니다. \n" +
                                "1. 공지사항의 전달 등 회사의 운영에 필요한 경우 \n" +
                                "2. 이용문의에 대한 회신, 불만의 처리 등 이용자에 대한 서비스 개선을 위한 경우 \n" +
                                "3. 회사의 서비스를 제공하기 위한 경우 \n" +
                                "4. 이벤트 및 행사 안내 등 마케팅을 위한 경우 \n" +
                                "5. 법령 및 회사 약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 \n" +
                                "운영에 지장을 주는 행위에 대한 방지 및 제재를 위한 경우 \n" +
                                "제8조(개인정보의 보유 및 이용기간) \n" +
                                "1. 회사는 이용자의 개인정보에 대해 개인정보의 수집·이용 목적이 달성을 위한 기간 동안 개인정보를 보유 \n" +
                                "및 이용합니다. \n" +
                                "2. 전항에도 불구하고 회사는 내부 방침에 의해 서비스 부정이용기록은 부정 가입 및 이용 방지를 위하여 회 \n" +
                                "원 탈퇴 시점으로부터 최대 1년간 보관합니다. \n" +
                                "제9조(법령에 따른 개인정보의 보유 및 이용기간) \n" +
                                "회사는 관계법령에 따라 다음과 같이 개인정보를 보유 및 이용합니다. \n" +
                                "1. 전자상거래 등에서의 소비자보호에 관한 법률에 따른 보유정보 및 보유기간 \n" +
                                "가. 계약 또는 청약철회 등에 관한 기록 : 5년 \n" +
                                "나. 대금결제 및 재화 등의 공급에 관한 기록 : 5년 다. 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년 라. 표시·광고에 관한 기록 : 6개월 \n" +
                                "2. 통신비밀보호법에 따른 보유정보 및 보유기간 \n" +
                                "가. 웹사이트 로그 기록 자료 : 3개월 \n" +
                                "3. 전자금융거래법에 따른 보유정보 및 보유기간 \n" +
                                "가. 전자금융거래에 관한 기록 : 5년 \n" +
                                "4. 위치정보의 보호 및 이용 등에 관한 법률 \n" +
                                "가. 개인위치정보에 관한 기록 : 6개월 \n" +
                                "제10조(개인정보의 파기원칙) \n" +
                                "회사는 원칙적으로 이용자의 개인정보 처리 목적의 달성, 보유·이용기간의 경과 등 개인정보가 필요하 \n" +
                                "지 않을 경우에는 해당 정보를 지체 없이 파기합니다. \n" +
                                "제11조(서비스 미이용자에 대한 개인정보처리) \n" +
                                "1. 회사는 1년 동안 회사의 서비스를 이용하지 않은 이용자의 개인정보는 원칙적으로 이용자에게 사전통지 \n" +
                                "하고 개인정보를 파기하거나 별도로 분리하여 저장합니다. \n" +
                                "2. 회사는 장기 미이용 이용자의 개인정보는 별도로 분리되어 안전하게 보관하게 되며, 해당 이용자의 통지 \n" +
                                "는 분리 보관 처리 일을 기준으로 최소 30일 이전에 전자우편주소로 전송됩니다. \n" +
                                "3. 장기 미이용 이용자는 회사가 미이용자 DB를 별도로 분리하기 전에 계속 서비스를 이용하고자 하는 경우 \n" +
                                "웹사이트(이하 '모바일앱' 포함)에 로그인하시면 됩니다. \n" +
                                "4. 장기 미이용 이용자는 웹사이트에 로그인할 경우 이용자의 동의에 따라 본인의 계정을 복원할 수 있습니 \n" +
                                "다. \n" +
                                "5. 회사는 분리 보관된 개인정보를 4년간 보관 후 지체없이 파기합니다. \n" +
                                "제12조(개인정보파기절차) \n" +
                                "1. 이용자가 회원가입 등을 위해 입력한 정보는 개인정보 처리 목적이 달성된 후 별도의 DB로 옮겨져(종이 의 경우 별도의 서류함) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참 조) 일정 기간 저장된 후 파기되어집니다. \n" +
                                "2. 회사는 파기 사유가 발생한 개인정보를 개인정보보호 책임자의 승인절차를 거쳐 파기합니다. \n" +
                                "제13조(개인정보파기방법) \n" +
                                "회사는 전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제하 \n" +
                                "며, 종이로 출력된 개인정보는 분쇄기로 분쇄하거나 소각 등을 통하여 파기합니다. \n" +
                                "제14조(광고성 정보의 전송 조치) \n" +
                                "1. 회사는 전자적 전송매체를 이용하여 영리목적의 광고성 정보를 전송하는 경우 이용자의 명시적인 사전동 \n" +
                                "의를 받습니다. 다만, 다음 각호 어느 하나에 해당하는 경우에는 사전 동의를 받지 않습니다 \n" +
                                "가. 회사가 재화 등의 거래관계를 통하여 수신자로부터 직접 연락처를 수집한 경우, 거래가 종료된 날로 부터 6개월 이내에 회사가 처리하고 수신자와 거래한 것과 동종의 재화 등에 대한 영리목적의 광고 \n" +
                                "성 정보를 전송하려는 경우 \n" +
                                "나. \n" +
                                "「방문판매 등에 관한 법률」에 따른 전화권유판매자가 육성으로 수신자에게 개인정보의 수집출처 를 고지하고 전화권유를 하는 경우 \n" +
                                "2. 회사는 전항에도 불구하고 수신자가 수신거부의사를 표시하거나 사전 동의를 철회한 경우에는 영리목적 \n" +
                                "의 광고성 정보를 전송하지 않으며 수신거부 및 수신동의 철회에 대한 처리 결과를 알립니다. \n" +
                                "3. 회사는 오후 9시부터 그다음 날 오전 8시까지의 시간에 전자적 전송매체를 이용하여 영리목적의 광고성 \n" +
                                "정보를 전송하는 경우에는 제1항에도 불구하고 그 수신자로부터 별도의 사전 동의를 받습니다. \n" +
                                "4. 회사는 전자적 전송매체를 이용하여 영리목적의 광고성 정보를 전송하는 경우 다음의 사항 등을 광고성 \n" +
                                "정보에 구체적으로 밝힙니다. \n" +
                                "가. 회사명 및 연락처 \n" +
                                "나. 수신 거부 또는 수신 동의 철회 의사표시에 관한 사항의 표시 \n" +
                                "5. 회사는 전자적 전송매체를 이용하여 영리목적의 광고성 정보를 전송하는 경우 다음 각 호의 어느 하나에 \n" +
                                "해당하는 조치를 하지 않습니다. \n" +
                                "가. 광고성 정보 수신자의 수신거부 또는 수신동의의 철회를 회피·방해하는 조치 \n" +
                                "나. 숫자·부호 또는 문자를 조합하여 전화번호·전자우편주소 등 수신자의 연락처를 자동으로 만들어 내 \n" +
                                "는 조치 \n" +
                                "다. 영리목적의 광고성 정보를 전송할 목적으로 전화번호 또는 전자우편주소를 자동으로 등록하는 조 \n" +
                                "치 \n" +
                                "라. 광고성 정보 전송자의 신원이나 광고 전송 출처를 감추기 위한 각종 조치 \n" +
                                "마. 영리목적의 광고성 정보를 전송할 목적으로 수신자를 기망하여 회신을 유도하는 각종 조치 \n" +
                                "제15조(개인정보 조회 및 수집동의 철회) \n" +
                                "1. 이용자 및 법정 대리인은 언제든지 등록되어 있는 자신의 개인정보를 조회하거나 수정할 수 있으며 개인 \n" +
                                "정보수집 동의 철회를 요청할 수 있습니다. \n" +
                                "2. 이용자 및 법정 대리인은 자신의 가입정보 수집 등에 대한 동의를 철회하기 위해서는 개인정보보호책임 \n" +
                                "자 또는 담당자에게 서면, 전화 또는 전자우편주소로 연락하시면 회사는 지체 없이 조치하겠습니다. \n" +
                                "제16조(개인정보 정보변경 등) \n" +
                                "1. 이용자는 회사에게 전조의 방법을 통해 개인정보의 오류에 대한 정정을 요청할 수 있습니다. \n" +
                                "2. 회사는 전항의 경우에 개인정보의 정정을 완료하기 전까지 개인정보를 이용 또는 제공하지 않으며 잘못 된 개인정보를 제3자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체 없이 통지하여 정정이 이루어지도록 하겠습니다. \n" +
                                "제17조(이용자의 의무) \n" +
                                "1. 이용자는 자신의 개인정보를 최신의 상태로 유지해야 하며, 이용자의 부정확한 정보 입력으로 발생하는 \n" +
                                "문제의 책임은 이용자 자신에게 있습니다. \n" +
                                "2. 타인의 개인정보를 도용한 회원가입의 경우 이용자 자격을 상실하거나 관련 개인정보보호 법령에 의해 \n" +
                                "처벌받을 수 있습니다. \n" +
                                "3. 이용자는 전자우편주소, 비밀번호 등에 대한 보안을 유지할 책임이 있으며 제3자에게 이를 양도하거나 대 \n" +
                                "여할 수 없습니다. \n" +
                                "제18조(회사의 개인정보 관리) \n" +
                                "회사는 이용자의 개인정보를 처리함에 있어 개인정보가 분실, 도난, 유출, 변조, 훼손 등이 되지 아니하 \n" +
                                "도록 안전성을 확보하기 위하여 다음과 같이 기술적·관리적 보호대책을 강구하고 있습니다. \n" +
                                "제19조(삭제된 정보의 처리) \n" +
                                "회사는 이용자 혹은 법정 대리인의 요청에 의해 해지 또는 삭제된 개인정보는 회사가 수집하는 \"개인정 \n" +
                                "보의 보유 및 이용기간\"에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리 \n" +
                                "하고 있습니다. \n" +
                                "제20조(비밀번호의 암호화) \n" +
                                "이용자의 비밀번호는 일방향 암호화하여 저장 및 관리되고 있으며, 개인정보의 확인, 변경은 비밀번호 \n" +
                                "를 알고 있는 본인에 의해서만 가능합니다. \n" +
                                "제21조(해킹 등에 대비한 대책) \n" +
                                "1. 회사는 해킹, 컴퓨터 바이러스 등 정보통신망 침입에 의해 이용자의 개인정보가 유출되거나 훼손되는 것 \n" +
                                "을 막기 위해 최선을 다하고 있습니다. \n" +
                                "2. 회사는 최신 백신프로그램을 이용하여 이용자들의 개인정보나 자료가 유출 또는 손상되지 않도록 방지하 \n" +
                                "고 있습니다. \n" +
                                "3. 회사는 만일의 사태에 대비하여 침입차단 시스템을 이용하여 보안에 최선을 다하고 있습니다. \n" +
                                "4. 회사는 민감한 개인정보를 수집 및 보유하고 있는 경우)를 암호화 통신 등을 통하여 네트워크상에서 개 \n" +
                                "인정보를 안전하게 전송할 수 있도록 하고 있습니다. \n" +
                                "제22조(개인정보 처리 최소화 및 교육) \n" +
                                "회사는 개인정보 관련 처리 담당자를 최소한으로 제한하며, 개인정보 처리자에 대한 교육 등 관리적 조 \n" +
                                "치를 통해 법령 및 내부방침 등의 준수를 강조하고 있습니다. \n" +
                                "제23조(개인정보 유출 등에 대한 조치) \n" +
                                "회사는 개인정보의 분실·도난·유출(이하 \"유출 등\"이라 한다) 사실을 안 때에는 지체 없이 다음 각 호의 \n" +
                                "모든 사항을 해당 이용자에게 알리고 방송통신위원회 또는 한국인터넷진흥원에 신고합니다. \n" +
                                "1. 유출 등이 된 개인정보 항목 \n" +
                                "2. 유출 등이 발생한 시점 \n" +
                                "3. 이용자가 취할 수 있는 조치 \n" +
                                "4. 정보통신서비스 제공자 등의 대응 조치 \n" +
                                "5. 이용자가 상담 등을 접수할 수 있는 부서 및 연락처 \n" +
                                "제24조(개인정보 유출 등에 대한 조치의 예외) \n" +
                                "회사는 전조에도 불구하고 이용자의 연락처를 알 수 없는 등 정당한 사유가 있는 경우에는 회사의 홈페 \n" +
                                "이지에 30일 이상 게시하는 방법으로 전조의 통지를 갈음하는 조치를 취할 수 있습니다. \n" +
                                "제25조(이용자의 쿠키 설치 선택권) \n" +
                                "1. 이용자는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서 이용자는 웹브라우저에서 옵션을 설정함 으로써 모든 쿠키를 허용하거나, 쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부 할 수도 있습니다. \n" +
                                "2. 다만, 쿠키의 저장을 거부할 경우에는 로그인이 필요한 회사의 일부 서비스는 이용에 어려움이 있을 수 있 \n" +
                                "습니다. \n" +
                                "제26조(쿠키 설치 허용 지정 방법) \n" +
                                "쿠키 설치 허용 여부를 지정하는 방법(Internet Explorer의 경우)은 다음과 같습니다. \n" +
                                "1. [도구] 메뉴에서 [인터넷 옵션]을 선택합니다. \n" +
                                "2. [개인정보 탭]을 클릭합니다. \n" +
                                "3. [고급] 설정에서 설정하시면 됩니다. \n" +
                                "제27조(회사의 개인정보 보호 책임자 지정) \n" +
                                "부칙 \n" +
                                "1. 회사는 이용자의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부 \n" +
                                "서 및 개인정보 보호책임자를 지정하고 있습니다. \n" +
                                "가. 개인정보 보호 책임자 \n" +
                                "1) 성명 : 조원희 \n" +
                                "2) 전화번호: 010-2009-7726 \n" +
                                "3) 이메일: onehee9710@gmail.com \n" +
                                "제1조 본 방침은 2023.05.08부터 시행됩니다. \n"
                        )
                    ) { item ->
                        Text(item)
                    }
                }
                Button(onClick = onConfirm) {
                    Text("확인")
                }
            }
        }
    }
}