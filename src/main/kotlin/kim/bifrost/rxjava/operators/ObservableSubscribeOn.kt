package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer
import kim.bifrost.rxjava.schedulers.Scheduler

/**
 * kim.bifrost.rxjava.operators.ObservableSubscribeOn
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 22:43
 */
class ObservableSubscribeOn<T: Any>(
    private val source: ObservableSource<T>,
    private val scheduler: Scheduler
) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        scheduler.scheduleDirect {
            source.subscribe(observer)
        }
    }

    companion object {
        fun <T: Any> Observable<T>.subscribeOn(scheduler: Scheduler): Observable<T> {
            return ObservableSubscribeOn(this, scheduler)
        }
    }
}