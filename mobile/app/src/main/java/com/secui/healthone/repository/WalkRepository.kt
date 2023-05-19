import com.secui.healthone.data.ApiResponse
import com.secui.healthone.data.WalkData
import com.secui.healthone.util.RetrofitClient
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WalkRepository {
    private val walkApiService = RetrofitClient.instance

    suspend fun getPastWeekWalkData(): List<Int> {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = LocalDate.now()
        val dateList = (1..6).map { today.minusDays(it.toLong()).format(dateFormatter) }

        val walkDataList = mutableListOf<Int>()
        for (date in dateList) {
            val response = walkApiService.getWalkData(dateTime = date)
            if (response.isSuccessful && response.body() != null) {
                val apiResponse = response.body()!!
                val stepsList = apiResponse.data.map { it.stepCount }
                walkDataList.addAll(stepsList)
            } else {
            }
        }
        return walkDataList.reversed()
    }
}