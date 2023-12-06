package designpattern.behavial_patterns.template_method

abstract class Page {

    fun printContent() {
        println("**********")
        printHeader()
        printBody()
        printFooter()
        println("**********")
    }

    protected abstract fun printHeader()

    protected abstract fun printBody()

    protected abstract fun printFooter()

}

class HTMLPage : Page() {
    override fun printHeader() {
        println("HTML Header")
    }

    override fun printBody() {
        println("HTML Body")
    }

    override fun printFooter() {
        println("HTML Footer")
    }
}

class PDFPage : Page() {
    override fun printHeader() {
        println("PDF Header")
    }

    override fun printBody() {
        println("PDF Body")
    }

    override fun printFooter() {
        println("PDF Footer")
    }
}

fun main() {
    val htmlPage = HTMLPage()
    val pdfPage = PDFPage()
    htmlPage.printContent()
    pdfPage.printContent()
}