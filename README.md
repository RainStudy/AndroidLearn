# MyHandler

> 按照Android Handler的大致思路 实现一个自己的handler

## Note

要想实现一个handler的功能，需要实现`Handler`,`Looper`,`Message`,`MessageQueue`

`Message`实际上是一个链表

暂时没有考虑异步消息的情况，之后完善

- Looper在线程上跑起来，不断从消息队列中提取消息，提取不到的时候堵塞
- 使用handler在别的线程上发送消息到消息队列
- looper提取到handler发送的信息，并根据handler设置的回调逻辑执行对应的代码