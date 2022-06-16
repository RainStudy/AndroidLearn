package kim.bifrost.rxjava

import kim.bifrost.rxjava.operators.ObservableCreate.Companion.create
import kim.bifrost.rxjava.operators.ObservableCreate.Companion.just
import kim.bifrost.rxjava.operators.ObservableDelay.Companion.delay
import kim.bifrost.rxjava.operators.ObservableFilter.Companion.filterNot
import kim.bifrost.rxjava.operators.ObservableFlatMap.Companion.flatMap
import kim.bifrost.rxjava.operators.ObservableMap.Companion.map
import kim.bifrost.rxjava.operators.ObservableObserveOn.Companion.observeOn
import kim.bifrost.rxjava.operators.ObservableRepeat.Companion.repeat
import kim.bifrost.rxjava.operators.ObservableSubscribeOn.Companion.subscribeOn
import kim.bifrost.rxjava.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun main() {
    Observable.create<Int> {
        onNext(1)
        onNext(2)
        onNext(3)
    }.map {
        println(it)
        it * 2
    }.flatMap {
        Observable.just(it, it, it)
    }.observeOn(Schedulers.io()).filterNot {
        it == 2
    }.repeat(2).delay(5, TimeUnit.SECONDS).subscribe {
        println(it)
    }
}