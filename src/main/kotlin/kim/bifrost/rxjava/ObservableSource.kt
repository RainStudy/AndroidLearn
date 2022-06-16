package kim.bifrost.rxjava

interface ObservableSource<T: Any> {
    fun subscribe(observer: Observer<in T>)
}