package me.ggulmool.shedlocktest

import net.javacrumbs.shedlock.core.LockAssert.assertLocked
import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ScheduledTask(
    @Value("\${application-name}") val applicationName: String,
    val lockProvider: LockProvider
) {

    @Scheduled(fixedRate = 10000L)
    @SchedulerLock(name = "reportCurrentTime", lockAtMostFor = "9s")
    fun reportCurrentTime() {
        Thread.sleep(2000L)
        println("$applicationName report ${LocalDateTime.now()}")
    }
}