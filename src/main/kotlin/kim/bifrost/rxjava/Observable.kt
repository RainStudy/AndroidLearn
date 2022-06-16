package kim.bifrost.rxjava

abstract class Observable<T: Any> : ObservableSource<T> {

    protected abstract fun subscribeActual(observer: Observer<in T>)

    override fun subscribe(observer: Observer<in T>) {
        subscribeActual(observer)
    }

    @JvmOverloads
    fun subscribe(onComplete: () -> Unit = {}, onError: (error: Throwable) -> Unit = {}, onNext: (T) -> Unit = {}) {
        subscribe(object : Observer<T> {
            override fun onNext(t: T) {
                onNext(t)
            }

            override fun onComplete() {
                onComplete()
            }

            override fun onError(e: Throwable) {
                onError(e)
            }
        })
    }

    companion object ProtoType
}