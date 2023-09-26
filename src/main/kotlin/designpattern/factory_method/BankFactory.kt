package designpattern.factory_method

class BankFactory {
    fun createBank(bankType: BankType): Bank {
        return when (bankType) {
            BankType.ABC_BANK -> ABCBank()
            BankType.DE_BANK -> DEBank()
            BankType.FGH_BANK -> FGHBank()
        }
    }
}