package kim.bifrost.rxjava

interface Emitter<T: Any> {
    fun onNext(value: T)

    fun onError(error: Throwable)

    fun onComplete()
}