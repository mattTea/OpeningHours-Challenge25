import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun convertOpeningHours(hoursSpecification: String): String {
    val hours = jacksonObjectMapper().readValue(hoursSpecification, HoursSpecification::class.java)

    val hoursGroups = hours.openingHoursSpecification.map {
        Triple(toDaysList(it.dayOfWeek), to12hour(it.opens), to12hour(it.closes))
    }

    return "${hoursGroups[0].first}: ${hoursGroups[0].second}-${hoursGroups[0].third}"
}

data class HoursSpecification(
    val openingHoursSpecification: List<HoursGroup>
)

data class HoursGroup(
    val dayOfWeek: List<String>,
    val opens: String,
    val closes: String
)

private fun to12hour(time: String): String {
    val hour = time.split(':').first().toInt()
    val minute = time.split(':').last().toInt()

    return when {
        (hour > 12 && minute > 0) -> "${hour - 12}.${minute}pm"
        (hour > 12 && minute == 0) -> "${hour - 12}pm"
        (hour <= 12 && minute > 0) -> "${hour}.${minute}am"
        else -> "${hour}am"
    }
}

private fun toDaysList(days: List<String>): String {
    return days[0].take(3)
}

//private enum class Days(val fullName: String) {
//    Mon("Monday"),
//    Tue("Tuesday"),
//    Wed("Wednesday"),
//    Thu("Thursday"),
//    Fri("Friday"),
//    Sat("Saturday"),
//    Sun("Sunday")
//}