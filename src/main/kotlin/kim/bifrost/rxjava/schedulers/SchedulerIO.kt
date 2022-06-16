package kim.bifrost.rxjava.schedulers

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * kim.bifrost.rxjava.schedulers.SchedulerIO
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 23:57
 */
class SchedulerIO : Scheduler() {

    private val executorService = Executors.newScheduledThreadPool(10)

    override fun scheduleDirect(delay: Long, unit: TimeUnit, runnable: Runnable) {
        executorService.schedule(runnable, delay, unit)
    }
}