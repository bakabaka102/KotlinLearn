package designpattern.structure_patterns.adapter_pattern

interface VietnameseTarget {

    fun send(words: String?)
}

class JapaneseAdaptee {
    fun receive(words: String?) {
        println("Retrieving words from Adapter ...")
        println(words)
    }
}

class TranslatorAdapter(var adaptee: JapaneseAdaptee? = null) : VietnameseTarget {

    override fun send(words: String?) {
        println("Reading Words ...")
        println(words)
        val vietnameseWords = translate(words)
        println("Sending Words ...")
        adaptee?.receive(vietnameseWords)
    }

    private fun translate(vietnameseWords: String?): String {
        println("$vietnameseWords is translated!")
        return "こんにちは"
    }
}

class VietnameseClient {

}

fun main() {
    val vietnameseTarget = TranslatorAdapter(JapaneseAdaptee())
    vietnameseTarget.send("Hi")
}