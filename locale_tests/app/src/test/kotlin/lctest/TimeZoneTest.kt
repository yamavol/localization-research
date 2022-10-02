package lctest

import org.junit.Test
import java.time.Instant
import java.time.ZoneId
import java.util.*

class TimeTest {
    @Test
    fun getCurrentTime() {
        println("java.time.Instant${Instant.now().epochSecond}")

    }
}

class TimeZoneListAvailableZoneID() {
    @Test
    fun listAvailableZoneId() {
        ZoneId.getAvailableZoneIds()
            .sorted()
            .forEach { zoneid ->
            println(zoneid)
        }
    }
}

class TimeZoneTest {
    @Test
    fun getCurrentTimeZone() {
        println("TimeZone.getDefault().id")
        println(TimeZone.getDefault().id)
    }
}