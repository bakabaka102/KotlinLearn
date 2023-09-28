package designpattern.factory_method

interface Bank {
    fun nameBank(): String
}

enum class BankType {
    ABC_BANK,
    DE_BANK,
    FGH_BANK,
}