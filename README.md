# MyRxJava

> 自己实现一个简单的rxjava

简单实现了rxjava的链式调用

## 实现的操作符

- map
- flatMap
- filter / filterNot
- repeat
- delay
- ***subscribeOn***
- ***observeOn***
- 生命周期回调

## Note

Scheduler部分直接偷懒用线程池实现了，不过其实应该也差不了多少, 线程切换的本质就是提交任务给其他线程的Scheduler，不过是rxjava巧妙的设计避免了回调地狱罢了。
`observeOn`和`subscribeOn`的区别就在于他们是在什么阶段向Scheduler提交任务。

- `subscribeOn`: 订阅是从下往上订阅，所以这时应该从下往上看，在subscribeOn上面的操作的线程被切换了
- `observeOn`: 而观察是从上到下观察的，所以应该从上往下看，在observeOn下面的操作的线程被切换了

我认为理清`subscribe`和`observe`这两个流程是学习Rxjava的关键之处。当我们调用ObservableSource的subscribe方法时，Observable会逐级向上subscribe，
直到到达最顶层的`ObservableOnSubscribe`，执行它的subscribe方法将调用我们在`ObservableCreate`中对`ObservableEmitter`的操作。随着`ObservableEmitter`
中`onNext`等方法的调用，参数被传递给下一级`Observable`的`Observer`，不同操作符实现的效果就是在包装Observer上做的文章。在`subscribeActual`中拿到上一级
`Observer`，在这一级中新建一个`Observer`并订阅它，这个Observer中的逻辑就是各种功能的实现。

- `subscribe` 订阅，这个操作一般主动调用。
- `observe` 观察，这个操作一般在订阅后回调。
