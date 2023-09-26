package designpattern.factory_method

class MainBankUse {
}

fun main() {
    val bankFactory = BankFactory()
    val bank = bankFactory.createBank(BankType.ABC_BANK)
    println(bank.nameBank())
}