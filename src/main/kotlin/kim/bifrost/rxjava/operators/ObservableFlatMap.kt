package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer

/**
 * kim.bifrost.rxjava.operators.ObservableFlatMap
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 16:31
 */
class ObservableFlatMap<T: Any, H: Any>(
    source: ObservableSource<T>,
    private val mapper: (e: T) -> Observable<H>
) : AbstractObservableWithUpstream<T, H>(source) {
    override fun subscribeActual(observer: Observer<in H>) {
        source.subscribe(FlatMapObserver(observer))
    }

    inner class FlatMapObserver(downstream: Observer<in H>) : BasicFuseableObserver<T, H>(downstream) {

        override fun onNext(t: T) {
            mapper(t).apply {
                subscribe {
                    downstream.onNext(it)
                }
            }
        }

        override fun onError(e: Throwable) {
            downstream.onError(e)
        }

        override fun onComplete() {

            downstream.onComplete()
        }

    }

    companion object {
        fun <T: Any, H: Any> Observable<T>.flatMap(mapper: (e: T) -> Observable<H>): Observable<H> {
            return ObservableFlatMap(this, mapper)
        }
    }
}