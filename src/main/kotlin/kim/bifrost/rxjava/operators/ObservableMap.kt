package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource
import kim.bifrost.rxjava.Observer

class ObservableMap<T: Any, H: Any>(
    source: ObservableSource<T>,
    private val mapper: (e: T) -> H
) : AbstractObservableWithUpstream<T, H>(source) {

    override fun subscribeActual(observer: Observer<in H>) {
        source.subscribe(MapObserver(observer))
    }

    inner class MapObserver(downstream: Observer<in H>) : BasicFuseableObserver<T, H>(downstream) {
        override fun onNext(t: T) {
            mapper(t).let {
                downstream.onNext(it)
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
        fun <T: Any, H: Any> Observable<T>.map(mapper: (e: T) -> H): Observable<H> {
            return ObservableMap(this, mapper)
        }
    }
}