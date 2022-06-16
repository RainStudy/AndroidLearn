package kim.bifrost.rxjava

interface Observer<T: Any> {
    fun onNext(t: T)

    fun onError(e: Throwable)

    fun onComplete()
}