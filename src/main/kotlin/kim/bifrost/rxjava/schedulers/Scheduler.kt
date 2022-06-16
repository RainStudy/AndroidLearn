package kim.bifrost.rxjava.schedulers

import java.util.concurrent.TimeUnit

/**
 * kim.bifrost.rxjava.schedulers.Scheduler
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 23:27
 */
abstract class Scheduler {
    abstract fun scheduleDirect(delay: Long, unit: TimeUnit, runnable: Runnable)

    fun scheduleDirect(runnable: Runnable) {
        scheduleDirect(0, TimeUnit.MILLISECONDS, runnable)
    }

    fun schedulePeriodicallyDirect(initialDelay: Long, period: Long, unit: TimeUnit, runnable: Runnable) {
        scheduleDirect(initialDelay, unit) {
            runnable.run()
            scheduleDirect(period, unit, runnable)
        }
    }
}