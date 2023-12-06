package designpattern.behavial_patterns.strategy_pattern

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


interface PayStrategy {
    fun pay(paymentAmount: Int): Boolean
    fun collectPaymentDetails()
}

class PayByPayPal : PayStrategy {

    private val DATA_BASE: MutableMap<String, String> = HashMap()
    private val READER = BufferedReader(InputStreamReader(System.`in`))
    private var email: String? = null
    private var password: String? = null
    private var signedIn = false

    init {
        DATA_BASE["amanda1985"] = "amanda@ya.com"
        DATA_BASE["qwerty"] = "john@amazon.eu"
    }

    override fun pay(paymentAmount: Int): Boolean {
        return if (signedIn) {
            println("Paying $paymentAmount using PayPal.")
            true
        } else {
            false
        }
    }

    override fun collectPaymentDetails() {
        try {
            while (!signedIn) {
                print("Enter the user's email: ")
                email = READER.readLine()
                print("Enter the password: ")
                password = READER.readLine()
                if (verify()) {
                    println("Data verification has been successful.")
                } else {
                    println("Wrong email or password!")
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private fun verify(): Boolean {
        setSignedIn(email == DATA_BASE[email] && password == DATA_BASE[password])
        return signedIn
    }

    private fun setSignedIn(signedIn: Boolean) {
        this.signedIn = signedIn
    }

}

class PayByCreditCard() : PayStrategy {

    private var card: CreditCard? = null

    private val READER = BufferedReader(InputStreamReader(System.`in`))

    override fun pay(paymentAmount: Int): Boolean {
        return if (cardIsPresent()) {
            println("Paying $paymentAmount using Credit Card.")
            card?.amount = card?.amount?.minus(paymentAmount) ?: 0
            true
        } else {
            false
        }
    }

    override fun collectPaymentDetails() {
        try {
            print("Enter the card number: ")
            val number = READER.readLine()
            print("Enter the card expiration date 'mm/yy': ")
            val date = READER.readLine()
            print("Enter the CVV code: ")
            val cvv = READER.readLine()
            card = CreditCard(number, date, cvv)

            // Validate credit card number...
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private fun cardIsPresent(): Boolean {
        return card != null
    }

}


class Order {
    var totalCost = 0
        set(cost) {
            field += cost
        }

    var isClosed = false
        private set

    fun processOrder(strategy: PayStrategy) {
        strategy.collectPaymentDetails()
    }

    fun setClosed() {
        isClosed = true
    }
}

data class CreditCard(
    private val number: String,
    private val date: String,
    private val cvv: String,
) {
    var amount = 100000
}


object Demo {
    private val priceOnProducts: MutableMap<Int, Int> = HashMap()
    private val reader = BufferedReader(InputStreamReader(System.`in`))
    private val order = Order()
    private var strategy: PayStrategy? = null

    init {
        priceOnProducts[1] = 2200
        priceOnProducts[2] = 1850
        priceOnProducts[3] = 1100
        priceOnProducts[4] = 890
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        while (!order.isClosed) {
            var cost: Int
            var continueChoice: String
            do {
                print(
                    "Please, select a product:" + "\n" +
                            "1 - Mother board" + "\n" +
                            "2 - CPU" + "\n" +
                            "3 - HDD" + "\n" +
                            "4 - Memory" + "\n"
                )
                val choice = reader.readLine().toInt()
                cost = (priceOnProducts[choice])!!
                print("Count: ")
                val count = reader.readLine().toInt()
                order.totalCost = cost * count
                print("Do you wish to continue selecting products? Y/N: ")
                continueChoice = reader.readLine()
            } while (continueChoice.equals("Y", ignoreCase = true))
            if (strategy == null) {
                println(
                    ("Please, select a payment method:" + "\n" +
                            "1 - PalPay" + "\n" +
                            "2 - Credit Card")
                )
                val paymentMethod = reader.readLine()

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                strategy = if ((paymentMethod == "1")) {
                    PayByPayPal()
                } else {
                    PayByCreditCard()
                }
            }

            // Order object delegates gathering payment data to strategy object,
            // since only strategies know what data they need to process a
            // payment.
            order.processOrder(strategy!!)
            print("Pay " + order.totalCost + " units or Continue shopping? P/C: ")
            val proceed = reader.readLine()
            if (proceed.equals("P", ignoreCase = true)) {
                // Finally, strategy handles the payment.
                if (strategy!!.pay(order.totalCost)) {
                    println("Payment has been successful.")
                } else {
                    println("FAIL! Please, check your data.")
                }
                order.setClosed()
            }
        }
    }
}