import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun main() {
    println(convertOpeningHours(openingHours))
}

fun convertOpeningHours(hoursSpecification: String): String {
    val hours = jacksonObjectMapper().readValue(hoursSpecification, HoursSpecification::class.java)

    val hoursGroups = hours.openingHoursSpecification.map {
        Triple(toDaysRange(it.dayOfWeek), to12hour(it.opens), to12hour(it.closes))
    }

    return combineHoursGroups(hoursGroups)
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
        (hour == 12 && minute > 0) -> "${hour}.${minute}pm"
        (hour == 12 && minute == 0) -> "${hour}pm"
        (hour < 12 && minute > 0) -> "${hour}.${minute}am"
        else -> "${hour}am"
    }
}

private fun toDaysRange(days: List<String>): String =
    days.map { it.take(3) }
        .filterIndexed { index, _ ->
            index == 0 || index == days.size - 1
        }
        .joinToString("-")

private fun combineHoursGroups(hoursGroups: List<Triple<String, String, String>>): String =
    hoursGroups.joinToString(" | ") {
        when (it.second == "0am" && it.third == "0am") {
            true -> "${it.first}: Closed"
            false -> "${it.first}: ${it.second}-${it.third}"
        }
    }