package kim.bifrost.handler

class Message {
    internal var `when` = 0L
    internal var next: Message? = null
    internal var target: Handler? = null
    var what = 0
}