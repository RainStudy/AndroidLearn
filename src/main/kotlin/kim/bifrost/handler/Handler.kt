package kim.bifrost.handler

abstract class Handler(looper: Looper) {
    private val mQueue by lazy {
        looper.mQueue
    }

    fun sendMessage(msg: Message) = sendMessageDelayed(msg, 0)

    fun sendMessageDelayed(msg: Message, delay: Long): Boolean {
        var delayMillis = delay
        if (delayMillis < 0) {
            delayMillis = 0
        }
        return sendMessageAtTime(msg, System.currentTimeMillis() + delayMillis)
    }

    fun sendMessageAtTime(msg: Message, uptimeMillis: Long): Boolean {
        msg.target = this
        mQueue.enqueueMessage(msg, uptimeMillis)
        return true
    }

    internal fun dispatchMessage(msg: Message) {
        handleMessage(msg)
    }

    /**
     * 处理发送来的消息
     *
     * @param msg
     */
    abstract fun handleMessage(msg: Message)
}