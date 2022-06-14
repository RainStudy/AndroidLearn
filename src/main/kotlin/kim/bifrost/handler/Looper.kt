package kim.bifrost.handler

class Looper private constructor() {

    internal val mQueue: MessageQueue = MessageQueue()
    internal val mInLoop = false
    private var mQuitting = false

    fun quitSafely() {
        mQuitting = true
    }

    companion object {
        private val sThreadLocal = ThreadLocal<Looper>()

        /**
         * 在当前线程上初始化looper
         */
        fun prepare() {
            if (sThreadLocal.get() != null) error("同一线程只能创建一个Looper")
            sThreadLocal.set(Looper())
        }

        /**
         * 开始loop
         */
        fun loop() {
            val me = myLooper() ?: error("请调用Looper.prepare()初始化当前线程上的Looper")
            while (true) {
                if (!loopOnce(me) || me.mQuitting) {
                    // 异常状态 退出loop
                    return
                }
            }
        }

        /**
         * 循环单次
         * 从队列中取出消息，并分发给handler
         *
         * @return 是否退出loop
         */
        private fun loopOnce(me: Looper): Boolean {
            val msg = me.mQueue.next()
            msg.target?.dispatchMessage(msg)
            return true
        }

        /**
         * 获取当前线程上looper
         *
         * @return looper
         */
        fun myLooper(): Looper? {
            return sThreadLocal.get()
        }
    }
}