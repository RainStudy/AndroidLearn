package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer
import kim.bifrost.rxjava.schedulers.Scheduler

/**
 * kim.bifrost.rxjava.operators.ObservableObserveOn
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/17 0:31
 */
class ObservableObserveOn<T: Any>(
    private val source: ObservableSource<T>,
    private val scheduler: Scheduler
) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        source.subscribe(object : Observer<T> {
            override fun onNext(t: T) {
                scheduler.scheduleDirect {
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
        fun <T: Any> Observable<T>.observeOn(scheduler: Scheduler): Observable<T> {
            return ObservableObserveOn(this, scheduler)
        }
    }
}