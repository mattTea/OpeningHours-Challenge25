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

    @Test
    fun `should return three consecutive days in single hours block`() {
        val openingHoursJson = """{
            "openingHoursSpecification": [
                {
                    "dayOfWeek": ["Monday", "Tuesday", "Wednesday"],
                    "opens": "10:00",
                    "closes": "18:00"
                }
            ]
        }"""

        val result = convertOpeningHours(openingHoursJson)

        val expected = "Mon-Wed: 10am-6pm"

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return 'closed' for a midnight-midnight hours`() {
        val openingHoursJson = """{
            "openingHoursSpecification": [
                {
                    "dayOfWeek": ["Monday"],
                    "opens": "00:00",
                    "closes": "00:00"
                }
            ]
        }"""

        val result = convertOpeningHours(openingHoursJson)

        val expected = "Mon: Closed"

        assertThat(result).isEqualTo(expected)
    }

    /*
    Further enhancements
    1. Is it possible to have 2 non-consecutive days in a single hoursBlock? Current implementation assumes not
    2. Will days in an hoursBlock always be presented in order? Current implementation assumes so
    */
}