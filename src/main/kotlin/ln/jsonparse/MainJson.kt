package ln.jsonparse

import helpers.printf
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

const val FILE_PATH = "E:\\DemoCode\\LangLearn\\src\\main\\kotlin\\ln\\jsonparse\\ui.json"

fun readFileByBufferedReader(fileName: String): List<String> = File(fileName).bufferedReader().readLines()

fun readFileAsTextByUseInputStream(filePath: String) = File(filePath).inputStream().readBytes().toString(Charsets.UTF_8)

fun parseJsonToUiJson(): UiJson {
    val json = readFileAsTextByUseInputStream(filePath = FILE_PATH)
    return Json.decodeFromString<UiJson>(json)
}

fun getSetUpMenu(ui: UiJson) {
    ui.setup.credit
}

fun main() {
    val ui = parseJsonToUiJson()
    printf("Value --- ${ui.setup.credit[2].dropDownValues}")
    //printf(parseJsonToUiJson())
}
