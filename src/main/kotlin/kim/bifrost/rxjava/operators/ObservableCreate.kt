package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Emitter
import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.Observer

class ObservableCreate<T: Any>(private val source: Emitter<T>.() -> Unit) : Observable<T>() {
    override fun subscribeActual(observer: Observer<in T>) {
        val parent = CreateEmitter(observer)

        try {
            parent.source()
        } catch (t: Throwable) {
            parent.onError(t)
        }
    }

    companion object {
        fun <T: Any> ProtoType.create(init: Emitter<T>.() -> Unit): Observable<T> {
            return ObservableCreate(init)
        }

        fun <T: Any> ProtoType.just(vararg values: T): Observable<T> {
            return create { values.forEach { onNext(it) } }
        }
    }

    inner class CreateEmitter(private val observer: Observer<in T>) : Emitter<T> {
        override fun onNext(value: T) {
            observer.onNext(value)
        }

        override fun onError(error: Throwable) {
            observer.onError(error)
        }

        override fun onComplete() {
            observer.onComplete()
        }
    }
}