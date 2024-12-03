package xmlhandle

import helpers.printf
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream
import java.io.PrintWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

fun main() {
    //GenerateDimenTool.generateFile()
    //testLV()
    //GenerateDimenTool.parserFileXml()

}

private fun testLV() {
    var a = 8
    printf("++a = ${++a}")
    printf("a++ = ${a++}")
    printf("a-- = ${a--}")
    val b = ++a - --a
    val c = a++ - a--
    printf("a == $a ------ b = $b --- c = $c")
    if (a < b) {
        printf("a < b")
    } else if (a > b) {
        printf("a > b")
    } else {
        printf("a == b")
    }
}

object GenerateDimenTool {

    fun generateFile() {
        val valueDimenHdpi = StringBuilder()
        //Add the tag at the beginning of xml
        /*val xmlStart = """<?xml version="1.0" encoding="utf-8"?>
        <resources>"""*/
        val xmlStart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
        val resourceTag = "\n<resources>\n"
        valueDimenHdpi.append(xmlStart).append(resourceTag)
        //Add content
        for (i in 0..600 step 2) {
            // The label name after name can be customized "margin_" to change at will
            val start = "    <dimen name=\"dp_dx800_${i}dp\">"
            val end = "dp</dimen>"
            valueDimenHdpi.append(start).append(i).append(end).append("\n")
        }
        valueDimenHdpi.append("\n\n")
        for (i in 0..600 step 2) {
            val startSP = "    <dimen name=\"sp_dx800_${i}sp\">"
            val endSP = "sp</dimen>"
            valueDimenHdpi.append(startSP).append(i).append(endSP).append("\n")
        }
        //Add the xml tail tag
        valueDimenHdpi.append("</resources>")
        val dimenHdpi = "./src/main/res/values-hdpi/dimens.xml"
        //val dimenHdpi = "./app/src/main/res/values/dimens.xml"
        val result = writeFile(dimenHdpi, valueDimenHdpi.toString())
        if (result.first) {
            printf("Done")
        } else {
            printf(result.second)
        }
    }

    private fun writeFile(file: String, text: String?): Pair<Boolean, String?> {
        var out: PrintWriter? = null
        return try {
            out = PrintWriter(BufferedWriter(FileWriter(file)))
            out.printf(text)
            Pair(true, null)
        } catch (e: IOException) {
            Pair(true, e.message)
        } finally {
            out?.close()
        }
    }

    private var empDataHashMap = HashMap<String, String>()
    private var empList: ArrayList<HashMap<String, String>> = ArrayList()

    @OptIn(ExperimentalStdlibApi::class)
    fun parserFileXml(inputStream: InputStream) {
        try {
            val builderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(inputStream)
            //reading the tag "employee" of empdetail file
            val nList = doc.getElementsByTagName("dimen")
            for (i in 0..<nList.length) {
                if (nList.item(0).nodeType == Node.ELEMENT_NODE) {
                    //creating instance of HashMap to put the data of node value
                    empDataHashMap = HashMap()
                    val element = nList.item(i) as Element
                    empDataHashMap["name"] = getNodeValue("dimen", element)
                    //adding the HashMap data to ArrayList
                    empList.add(empDataHashMap)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }
    }


    // function to return node value
    private fun getNodeValue(tag: String, element: Element): String {
        val nodeList = element.getElementsByTagName(tag)
        val node = nodeList.item(0)
        if (node != null) {
            if (node.hasChildNodes()) {
                val child = node.firstChild
                while (child != null) {
                    if (child.nodeType == Node.TEXT_NODE) {
                        return child.nodeValue
                    }
                }
            }
        }
        return ""
    }

}

