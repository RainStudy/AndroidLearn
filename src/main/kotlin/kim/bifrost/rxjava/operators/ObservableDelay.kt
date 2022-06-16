package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer
import kim.bifrost.rxjava.schedulers.Scheduler
import kim.bifrost.rxjava.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * kim.bifrost.rxjava.operators.ObservableDelay
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/17 0:39
 */
class ObservableDelay<T: Any>(
    private val source: ObservableSource<T>,
    private val scheduler: Scheduler,
    private val delay: Long,
    private val unit: TimeUnit
) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        source.subscribe(object : Observer<T> {
            override fun onNext(t: T) {
                scheduler.scheduleDirect(delay, unit) {
                    observer.onNext(t)
                }
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

            override fun onComplete() {
                observer.onComplete()
            }
        })
    }

    companion object {
        @JvmOverloads
        fun <T: Any> Observable<T>.delay(delay: Long, unit: TimeUnit, scheduler: Scheduler = Schedulers.default()): Observable<T> {
            return ObservableDelay(this, scheduler, delay, unit)
        }
    }
}