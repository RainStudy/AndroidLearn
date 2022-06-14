package kim.bifrost.handler

import kotlin.math.min

class MessageQueue {

    private var messages: Message? = null

    /**
     * 将消息插入消息队列
     *
     * @param msg 消息
     * @param when 执行时间
     */
    fun enqueueMessage(msg: Message, `when`: Long) {
        msg.`when` = `when`
        var p = messages
        // 在消息队列为空时 || 这条消息的when没有初始化时 || 这条消息的when小于队列头的when(即应该把消息放到队列头)
        if (p == null || `when` == 0L || `when` < p.`when`) {
            // 将消息放置到队列头
            msg.next = p
            messages = msg
        } else {
            // 将消息队列walk一遍，将消息放置到队列尾部
            var prev: Message? = null
            while (true) {
                prev = p
                p = p?.next
                // 为null则代表walk到尾部了，break出循环
                if (p == null || `when` < p.`when`) {
                    break;
                }
            }
            // 插入队尾
            msg.next = p
            prev?.next = msg
        }
    }

    /**
     * 取出队列第一条消息
     * 若为null则堵塞等待
     *
     * @return
     */
    fun next(): Message {
        var nextPollTimeoutMills = 0
        //如果暂时没有消息，就先阻塞住，会等到插入新消息的时候唤醒
        //或者有消息，但是链表的头节点的那条消息还要等待一段时间才要执行
        //那就计算出这个时间，然后阻塞这么长一个时间
        while (true) {
            // 上锁
            synchronized(this) {
                val now = System.currentTimeMillis()
                val msg = messages
                if (msg != null) {
                    if (now < msg.`when`) {
                        // 仍需等待 计算出需要等待到的时间
                        nextPollTimeoutMills = min(msg.`when` - now, Integer.MAX_VALUE.toLong()).toInt()
                    } else {
                        // 无需等待，直接取出消息并返回
                        messages = msg.next
                        // 清除掉msg的链表关系，确保msg能够被正常回收
                        msg.next = null
                        return msg
                    }
                } else {
                    // 如果为空则清除掉之前消息的nextPollTimeoutMills
                    nextPollTimeoutMills = -1
                }
            }
        }
    }
}