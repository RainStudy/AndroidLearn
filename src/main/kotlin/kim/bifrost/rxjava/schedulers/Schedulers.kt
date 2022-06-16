package kim.bifrost.rxjava.schedulers

/**
 * kim.bifrost.rxjava.schedulers.Schedulers
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/17 0:25
 */
object Schedulers {
    private val io = SchedulerIO()
    private val default = SchedulerDefault()

    fun io(): SchedulerIO {
        return io
    }

    fun default(): SchedulerDefault {
        return default
    }
}