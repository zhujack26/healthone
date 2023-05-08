package com.secui.healthone.ui.login

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun PolicyDialog(onConfirm: () -> Unit) {
    AlertDialog(
        title = { Text("개인정보 처리방침") },
        text = {
            LazyColumn {
                item {
                    Text(
                        " 이산가족 >('healthone.com'이하 'healthone')은(는) 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.\n" +
                                "\n" +
                                "○ 이 개인정보처리방침은 2023년 5월 3부터 적용됩니다.\n" +
                                "\n" +
                                "\n" +
                                "제1조(개인정보의 처리 목적)\n" +
                                "\n" +
                                "< 이산가족 >('healthone.com'이하 'healthone')은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.\n" +
                                "\n" +
                                "1. 홈페이지 회원가입 및 관리\n" +
                                "\n" +
                                "회원 가입의사 확인 목적으로 개인정보를 처리합니다.\n" +
                                "\n" +
                                "\n" +
                                "2. 민원사무 처리\n" +
                                "\n" +
                                "민원인의 신원 확인 목적으로 개인정보를 처리합니다.\n" +
                                "\n" +
                                "\n" +
                                "3. 재화 또는 서비스 제공\n" +
                                "\n" +
                                "서비스 제공을 목적으로 개인정보를 처리합니다.\n" +
                                "\n" +
                                "\n" +
                                "4. 마케팅 및 광고에의 활용\n" +
                                "\n" +
                                "신규 서비스(제품) 개발 및 맞춤 서비스 제공 등을 목적으로 개인정보를 처리합니다.\n" +
                                "\n" +
                                "\n" +
                                "5. 개인영상정보\n" +
                                "\n" +
                                "범죄의 예방 및 수사 등을 목적으로 개인정보를 처리합니다."
                    )
                }
            }
        },
                onDismissRequest = { onConfirm() },
                buttons = {
                    TextButton(onClick = onConfirm) {
                        Text("확인")
                    }
                }
            )
}