package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer

/**
 * kim.bifrost.rxjava.operators.ObservableRepeat
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 18:22
 */
class ObservableRepeat<T: Any>(
    private val source: ObservableSource<T>,
    private val repeat: Int
) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        source.subscribe(object : Observer<T> {

            override fun onNext(t: T) {
                repeat(repeat) {
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
        fun <T: Any> Observable<T>.repeat(repeat: Int): Observable<T> {
            return ObservableRepeat(this, repeat)
        }
    }
}