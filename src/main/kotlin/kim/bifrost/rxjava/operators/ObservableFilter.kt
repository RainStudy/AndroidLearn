package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer

/**
 * kim.bifrost.rxjava.operators.ObservableFilter
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 18:12
 */
class ObservableFilter<T: Any>(
    private val source: ObservableSource<T>,
    private val filter: (T) -> Boolean,
    private val reversed: Boolean = false
) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        source.subscribe(object : Observer<T> {
            override fun onNext(t: T) {
                var res = filter(t)
                if (reversed) {
                    res = !res
                }
                if (res) {
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
        fun <T: Any> Observable<T>.filter(filter: (T) -> Boolean): Observable<T> {
            return ObservableFilter(this, filter)
        }

        fun <T: Any> Observable<T>.filterNot(filter: (T) -> Boolean): Observable<T> {
            return ObservableFilter(this, filter, true)
        }
    }
}