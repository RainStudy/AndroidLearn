package kim.bifrost.rxjava.schedulers

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * kim.bifrost.rxjava.schedulers.SchedulerDefault
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/17 0:23
 */
class SchedulerDefault : Scheduler() {

    private val executorService = Executors.newScheduledThreadPool(1)

    override fun scheduleDirect(delay: Long, unit: TimeUnit, runnable: Runnable) {
        executorService.schedule(runnable, delay, unit)
    }
}