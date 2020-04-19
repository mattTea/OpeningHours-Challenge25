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
}