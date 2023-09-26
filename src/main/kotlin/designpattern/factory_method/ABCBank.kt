package designpattern.factory_method

class ABCBank : Bank {
    override fun nameBank(): String {
        return ABCBank::class.java.simpleName
    }
}

class DEBank : Bank {
    override fun nameBank(): String {
        return DEBank::class.java.simpleName
    }

}

class FGHBank : Bank {
    override fun nameBank(): String {
        return FGHBank::class.java.simpleName
    }
}