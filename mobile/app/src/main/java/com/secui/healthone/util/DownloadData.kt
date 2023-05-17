package com.secui.healthone.util

import android.content.Context
import com.secui.healthone.api.DataApi
import com.secui.healthone.instance.DataDownInstance

suspend fun DownloadData(context: Context, url: String, accessToken: String) {
    val retrofit = DataDownInstance("https://back.apihealthone.com/")
    val service = retrofit.create(DataApi::class.java)

    val responseBody = DownloadData(accessToken)

    // 파일 저장
    val file = File(context.filesDir, "downloaded_data.txt")
    val outputStream = FileOutputStream(file)
    outputStream.write(responseBody.bytes())
    outputStream.close()

    // 다운로드 완료 후 파일 열기
    val uri = Uri.fromFile(file)
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(uri, "application/octet-stream")
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}