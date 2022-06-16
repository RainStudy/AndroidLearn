package kim.bifrost.rxjava.operators

import kim.bifrost.rxjava.Observer

/**
 * kim.bifrost.rxjava.operators.BasicFuseableObserver
 * myRxjava
 *
 * @author 寒雨
 * @since 2022/6/16 16:11
 *
 * @param T 上文
 * @param R 下文
 */
abstract class BasicFuseableObserver<T: Any, R: Any>(protected val downstream: Observer<in R>) : Observer<T> {

}