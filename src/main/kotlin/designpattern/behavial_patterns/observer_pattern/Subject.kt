package designpattern.behavial_patterns.observer_pattern

interface Observer {
    fun update(user: User)
}

interface Subject {

    fun attach(observer: Observer)

    fun detach(observer: Observer)

    fun notifyAllObserver()
}

enum class LoginStatus {
    SUCCESS,
    FAILURE,
    INVALID,
    EXPIRED,
}

data class User(
    var email: String? = null,
    var ip: String? = null,
    var status: LoginStatus? = null,
)

class AccountService(email: String?, ip: String?) : Subject {

    private val user: User = User(email = email, ip = ip)
    private val observers: MutableList<Observer> = ArrayList()

    override fun attach(observer: Observer) {
        if (!observers.contains(observer)) {
            observers.add(observer)
        }
    }

    override fun detach(observer: Observer) {
        if (observers.contains(observer)) {
            observers.remove(observer)
        }
    }

    override fun notifyAllObserver() {
        for (observer in observers) {
            observer.update(user)
        }
    }

    fun changeStatus(status: LoginStatus?) {
        user.status = status
        println("Status is changed")
        notifyAllObserver()
    }

    fun login() {
        when {
            !isValidIP() -> {
                user.status = LoginStatus.INVALID
            }

            isValidEmail() -> {
                user.status = LoginStatus.SUCCESS
            }

            else -> {
                user.status = LoginStatus.FAILURE
            }
        }
        println("Login is handled")
        notifyAllObserver()
    }

    private fun isValidIP(): Boolean = "127.0.0.1" == user.ip

    private fun isValidEmail(): Boolean = "contact@gpcoder.com".equals(user.email, ignoreCase = true)
}

class Logger : Observer {
    override fun update(user: User) {
        println("Logger: $user")
    }
}

class Mailer : Observer {
    override fun update(user: User) {
        if (user.status == LoginStatus.EXPIRED) {
            println("Mailer: User ${user.email} is expired. An email was sent!")
        }
    }
}


class Protector : Observer {
    override fun update(user: User) {
        if (user.status == LoginStatus.INVALID) {
            println("Protector: User ${user.email} is invalid. IP ${user.ip} is blocked")
        }
    }
}

fun main() {
    val account1 = createAccount("contact@gpcoder.com", "127.0.0.1")
    account1.login()
    account1.changeStatus(LoginStatus.EXPIRED)
    println("***********NEXT************")
    val account2 = createAccount("contact@gpcoder.com", "116.108.77.231")
    account2.login()
}

fun createAccount(email: String, ip: String): AccountService {
    val account = AccountService(email, ip)
    account.attach(Logger())
    account.attach(Mailer())
    account.attach(Protector())
    return account
}