import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class OpeningHoursConverterKtTest {
    @Test
    fun `should return single day opening hours`() {
        val openingHoursJson = """{
            "openingHoursSpecification": [
                {
                    "dayOfWeek": ["Monday"],
                    "opens": "10:00",
                    "closes": "18:00"
                }
            ]
        }"""

        val result = convertOpeningHours(openingHoursJson)

        val expected = "Mon: 10am-6pm"

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return two opening hours blocks with single day in each`() {
        val openingHoursJson = """{
            "openingHoursSpecification": [
                {
                    "dayOfWeek": ["Monday"],
                    "opens": "10:00",
                    "closes": "18:00"
                },
                {
                    "dayOfWeek": ["Saturday"],
                    "opens": "09:00",
                    "closes": "22:00"
                }
            ]
        }"""

        val result = convertOpeningHours(openingHoursJson)

        val expected = "Mon: 10am-6pm | Sat: 9am-10pm"

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return two consecutive days in single hours block`() {
        val openingHoursJson = """{
            "openingHoursSpecification": [
                {
                    "dayOfWeek": ["Monday", "Tuesday"],
                    "opens": "10:00",
                    "closes": "18:00"
                }
            ]
        }"""

        val result = convertOpeningHours(openingHoursJson)

        val expected = "Mon-Tue: 10am-6pm"

        assertThat(result).isEqualTo(expected)
    }

    // 2 non-consecutive days

    // 3 consecutive days
}