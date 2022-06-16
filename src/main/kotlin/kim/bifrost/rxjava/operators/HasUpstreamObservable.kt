package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.ObservableSource

/**
 * kim.bifrost.rxjava.operators.HasUpstreamObservable
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 15:31
 */
interface HasUpstreamObservable<T: Any> {
    fun source(): ObservableSource<T>
}