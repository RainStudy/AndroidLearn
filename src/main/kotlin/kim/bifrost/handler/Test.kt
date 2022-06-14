package kim.bifrost.handler

import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main() {
    println("主线程: ${Thread.currentThread().name}")
    Looper.prepare()
    val looper = Looper.myLooper()!!
    val handler = object : Handler(looper) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> println("巴拉巴拉巴拉巴拉")
                1 -> println("阿吧阿吧阿吧阿吧")
            }
            println("当前线程: ${Thread.currentThread().name}")
        }
    }
    thread {
        handler.sendMessage(Message().apply { what = 0 })
        Thread.sleep(TimeUnit.SECONDS.toMillis(5))
        handler.sendMessage(Message().apply { what = 1 })
        looper.quitSafely()
    }
    Looper.loop()
}