package designpattern.behavial_patterns.observer_pattern

import java.io.File


interface EventListener {
    fun update(eventType: String?, file: File?)
}

class EventManager(vararg operations: String) {

    private var listeners = mutableMapOf<String, MutableList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String, listener: EventListener) {
        val users = listeners.getOrDefault(eventType, mutableListOf())
        users.add(listener)
    }

    fun unsubscribe(eventType: String?, listener: EventListener) {
        val users: MutableList<EventListener> = listeners.getOrDefault(eventType, mutableListOf())
        users.remove(listener)
    }

    fun notify(eventType: String, file: File?) {
        val users = listeners.getOrDefault(eventType, mutableListOf())
        for (listener in users) {
            listener.update(eventType, file)
        }
    }
}

class Editor(
    var events: EventManager? = null,
    private var file: File? = null,
) {

    init {
        events = EventManager("open", "save")
    }

    fun openFile(filePath: String) {
        file = File(filePath)
        events?.notify("open", file)
    }

    @Throws(Exception::class)
    fun saveFile() {
        if (file != null) {
            events?.notify("save", file)
        } else {
            throw Exception("Please open a file first.")
        }
    }
}

class EmailNotificationListener(private var email: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email: Someone has performed $eventType operation with the following file: ${file?.getName()}")
    }

}

class LogOpenListener (private var fileName: String) : EventListener {

    private var log: File? = null

    init {
        log = File(fileName)
    }

    override fun update(eventType: String?, file: File?) {
        println("Save to log  $log: Someone has performed $eventType operation with the following file: ${file?.getName()}")
    }
}

fun main() {
    val editor = Editor()
    editor.events?.subscribe("open", LogOpenListener("/path/to/log/file.txt"))
    editor.events?.subscribe("save", EmailNotificationListener("admin@example.com"))
    try {
        editor.openFile("test.txt")
        editor.saveFile()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}