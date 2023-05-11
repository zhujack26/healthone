package com.secui.healthone.util

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.time.LocalDateTime

// 기록들을 저장하고 불러오기 위한 DB 클래스
class DBHelper: SQLiteOpenHelper {

    // 생성자 ( 데이터 베이스의 이름을 정해줌. )
    constructor(context: Context) :super(context, "health_one.db", null, 1);

    // 데이터 베이스 파일이 생성될 때 자동 호출되는 메서드
    // 테이블 생성 코드를  작성한다
    // 데이터 베이스 파일이 생성될 때 호출
    override fun onCreate(p0: SQLiteDatabase?) {
        // 생성 쿼리문 작성
        // 메모사항
        // db의 level 열에는 1~6 까지의 값을 가지며 레벨 순으로 값을 비교해야해서 레벨 컬럼을 추가함
        val sql = """
            create table sleep_record(
            	slee_record_idx integer primary key autoincrement,
                record_sleep_time int not null,
                rec_time_log TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP 
            );
        """.trimIndent()
        p0?.execSQL(sql);
    }

    // 데이터 베이스가 업데이트?? 될때 호출
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    data class TimeDBLog(val idx:Int, val recordSleepTime:Int, val recTimeLog:String);
    // db 출력
    fun selectAll(context: Context, time: LocalDateTime = LocalDateTime.now()): MutableList<TimeDBLog> {

        val dbHelper = DBHelper(context = context)
        val query = """
                            SELECT slee_record_idx, record_sleep_time, rec_time_log
                            FROM sleep_record
                            WHERE rec_time_log BETWEEN ? AND ?
                        """.trimIndent()

        val endDate = time.toLocalDate().atStartOfDay()
        val startDate = endDate.minusDays(1).plusSeconds(1)
        val args = arrayOf(startDate.toString(), endDate.toString())

        val recordTimeList: MutableList<TimeDBLog> = mutableListOf() // 지난 1일간의
        val tableData: Cursor = dbHelper.writableDatabase.rawQuery(query, args)

        while (tableData.moveToNext()) {
            val srI = tableData.getColumnIndex("sleep_record_idx")
            val rstI = tableData.getColumnIndex("record_sleep_time")
            val rtI = tableData.getColumnIndex("rec_time_log")

            val sleepIndex = tableData.getInt(srI)
            val recordSleepTime = tableData.getInt(rstI)
            val recordTimeLog = tableData.getString(rtI)
            val timeLog = TimeDBLog(idx = sleepIndex, recordSleepTime = recordSleepTime, recTimeLog = recordTimeLog)
            recordTimeList.add(timeLog)
        }

        dbHelper.writableDatabase.close()
        return recordTimeList
    }
    fun saveScore(context: Context, recordSleepTime: Int){
        // SQLite로 데이터를 삽입하는 코드
        // step 1. 데이터 베이스를 연다
        val dbHelper = DBHelper(context);
        // step 2. 삽입 코드를 작성한다
        val sql = """
            insert into sleep_record
            (record_sleep_time)
            values
            (?)
        """.trimIndent()
        // step 3. 세팅될 값을 배열로 선언해준다
        val values = arrayOf(recordSleepTime);
        // step 4. DBHelper를 통해 쿼리문을 실행한다.
        dbHelper.writableDatabase.execSQL(sql, values); // sql문, 값(배열) 순이다.
        // step 5. DB 사용이 끝났다면 쿼리문을 닫아 준다.
        dbHelper.writableDatabase.close();
    }


//
//    // 유저의 플레이 타임 기록을 저장하는 메서드!
//    // scoreBoard 를 통해서 저장한다.
//
//    // 유저의 과거 기록을 통해 간단한 통계정보 제공하는 메서드
//    // 두 가지 정보를 제공
//    // 전체 평균보다 빨라졌는가?
//    fun getStaticData(context:Context){
//        // SQLite로 데이터 베이스의 값을 가져오는 코드 (select)
//        // step 1. DBHelper 객체를 생성한다.
//        val dbHelper = DBHelper(context = context);
//
//        // step 2. sql 쿼리문을 작성한다.
//        val query = """
//            select avg(avg_time) as all_avg, avg(total_time) as all_total
//            from math_rec where level = ? and round_idx = ?;
//        """.trimIndent();
//
//        // step 3. sql문을 실행하고 결과 객체를 받아온다
//        // 쿼리문에 추가적인 조건 등을 넣지 않았기 때문에, null을 준다. 조건이 있다면 배열로 주면 됨.
//        // 이 데이터의 자료형은 Cursor이다.
//        val args = arrayOf(PageIndexComp.playLevel.toString(), PageIndexComp.pageIdx.toString()); // 인자는 String이어야 함.
//        val tableData: Cursor = dbHelper.writableDatabase.rawQuery(query, args);
//
//        // step 4. 데이터 꺼내오기
//        // 데이터는 반복문을 통해 값을 꺼내와야 한다.
//        // 가져올 데이터는 tableData에 있는데 이러한 값 하나하나를 참조하는데 쓸 key 값은 int 이며 index라고 부른다.
//        // 따라서 네이밍 규칙에 Index를 붙여주어서 구분해보았다.
//        // SQLite로 부터 얻어낸 Cursor 객체로부터 바로 값을 가져오려하면
//        // 커서의 위치가 가장 첫 데이터를 향하고 있지 않기 때문에
//        // 반복문을 통해서 커서를 이동시키고 그 이후에 값을 가져오는 식으로 진행해야 한다.
//        // 따라서 값이 한개 던지 여러 개던지 while문 등을 사용하여 값을 가져와야 한다!
//
//        while(tableData.moveToNext()){
//            val allAvgIndex:Int = tableData.getColumnIndex("all_avg"); // 가져올 컬럼명을 인자로 준다.
//            val allTotalIndex:Int = tableData.getColumnIndex("all_total");
//
//            // getString 데이터를 통해 DB 속 값을 가져온다
//            val allAvgTime = tableData.getFloat(allAvgIndex);
//            val allTotalTime = tableData.getFloat(allTotalIndex);
//
//            // step 5. 실제 값을 사용하고 싶은 곳에 사용!
//            // null 체크!
//            if (allAvgTime == null || allAvgTime == 0f) {
//                allAvgTextView.text = "기록없음";
//            }else {
//                val avg = String.format("%.2f", allAvgTime);
//                allAvgTextView.text = "$avg 초"
//            }
//
//            if (allTotalTime == null || allAvgTime == 0f) {
//                allTotalTextView.text = "기록없음";
//            }else {
//                val total = String.format("%.1f", allTotalTime);
//                allTotalTextView.text = "$total 초"
//            }
//
//        }
//        // 사용후에는 자원을 닫는다.
//        dbHelper.writableDatabase.close();
//    }
//
//    // 직전보다 빨라졌나요?
//    fun getPastData(pastAvgTextView: TextView, pastTotalTextView: TextView){
//        // step 1. DBHelper 객체를 생성한다.
//        val dbHelper = DBHelper(this);
//
//        // step 2. sql문을 작성한다.
//        val query = """
//            select avg_time, total_time
//            from math_rec where level = ? and round_idx = ?;
//        """.trimIndent();
//
//        // step 3. Cursor 객체를 가져온다.
//        val args = arrayOf(PageIndexComp.playLevel.toString(), PageIndexComp.pageIdx.toString()); // 인자는 String이어야 함.
//        val pastData: Cursor = dbHelper.writableDatabase.rawQuery(query, args);
//
//        var isEmpty = true;
//        // step 4. 데이터를 추출한다.
//        while(pastData.moveToNext()){
//            val avgTimeIndex:Int = pastData.getColumnIndex("avg_time"); // 가져올 컬럼명을 인자로 준다.
//            val totalTimeIndex:Int = pastData.getColumnIndex("total_time");
//            isEmpty = false;
//
//            // getString 데이터를 통해 DB 속 값을 가져온다
//            val prevAvgTime = pastData.getFloat(avgTimeIndex);
//            val prevTotalTime = pastData.getFloat(totalTimeIndex);
//
//            // step 5. 실제 값을 사용하고 싶은 곳에 사용!
//            val prevAvg = String.format("%.2f", prevAvgTime);
//            val prevTotal = String.format("%.1f", prevTotalTime);
//            pastAvgTextView.text = "$prevAvg 초";
//            pastTotalTextView.text = "$prevTotal 초";
//        }
//
//        // 기록이 없을 경우의 값 세팅
//        if(isEmpty){
//            pastAvgTextView.text = "기록없음";
//            pastTotalTextView.text = "기록없음";
//        }
//
//        // 사용후에는 자원을 닫는다.
//        dbHelper.writableDatabase.close();
//    }

}