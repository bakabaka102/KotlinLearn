package designpattern.behavial_patterns.strategy_pattern

data class Book(val name: String, val author: String)

interface BookComparator {

    fun compareBook(book1: Book, book2: Book): Int
}


class BookSortName : BookComparator {
    override fun compareBook(book1: Book, book2: Book): Int {
        return book1.name.compareTo(book2.name)
    }
}

class BookSortAuthor : BookComparator {
    override fun compareBook(book1: Book, book2: Book): Int {
        return book1.author.compareTo(book2.author)
    }
}

class BookManagement {

    companion object {
        const val MAX_SIZE_BOOK = 1000
    }

    private val items: MutableList<Book> = ArrayList()

    fun add(book: Book): Boolean {
        return if (items.size < MAX_SIZE_BOOK) {
            items.add(book)
        } else {
            false
        }
    }

    fun getBook(index: Int): Book? {
        return if (index >= 0 && index < items.size) {
            items[index]
        } else {
            null
        }
    }

    fun getListBook(): MutableList<Book> {
        return items
    }

    fun sortBook(bookComparator: BookComparator) {
        for (i in 0 until items.size) {
            for (j in i + 1 until items.size) {
                if (bookComparator.compareBook(items[i], items[j]) > 0) {
                    val tempBook = items[i]
                    items[i] = items[j]
                    items[j] = tempBook
                }
            }
        }
    }
}

fun main() {
    val bookManagement = BookManagement()
    bookManagement.add(Book("Lao Hac", "Nam Cao"))
    bookManagement.add(Book("Chi Pheo", "Nam Cao"))
    bookManagement.add(Book("Dem truoc binh minh", "Lu Tu Hao"))
    bookManagement.add(Book("Dan than", "Sherry"))
    bookManagement.add(Book("Conan", "Gosho Aoyama"))
    //Not order
    println("Not sort: ${bookManagement.getListBook()}")
    //Sort by Name
    val bookSortName = BookSortName()
    bookManagement.sortBook(bookSortName)
    println("Sort by name: ${bookManagement.getListBook()}")
    //Sort by Author
    val bookSortAuthor = BookSortAuthor()
    bookManagement.sortBook(bookSortAuthor)
    println("Sort by Author: ${bookManagement.getListBook()}")
}

