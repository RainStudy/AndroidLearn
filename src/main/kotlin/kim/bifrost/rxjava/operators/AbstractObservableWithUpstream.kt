package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observable
import kim.bifrost.rxjava.ObservableSource

/**
 * kim.bifrost.rxjava.operators.AbstractObservableWithUpstream
 * myRxjava
 * transformer类型操作符的抽象类
 *
 * @author 寒雨
 * @since 2022/6/16 15:28
 */
abstract class AbstractObservableWithUpstream<T: Any, U: Any>(
    protected val source: ObservableSource<T>
) : Observable<U>(), HasUpstreamObservable<T> {
    override fun source(): ObservableSource<T> = source
}