package asynchronous

import helpers.printf
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun performObservableJust() {
    val observable = Observable.just("Task 1", "Task 2", "Task 3")
    observable.subscribe(
        { event -> printf("Received: $event") },
        { error -> error.printStackTrace() },
        { printf("All events received") })
}

/**
 * concat: Đảm bảo các luồng dữ liệu được kết hợp sẽ phát ra giá trị theo thứ tự tuần tự.
 * Sử dụng với Observable: Kết hợp nhiều Observable để phát ra các giá trị theo thứ tự tuần tự.
 * Sử dụng với Single: Chuyển đổi Single thành Observable để có thể sử dụng concat.
 */
fun performObservableConcat() {
    //Case 1
    val observable1 = Observable.just("A1", "A2", "A3")
    val observable2 = Observable.just("B1", "B2", "B3")
    //Result: A1, A2, A3, A4, B1, B2, B3, B4
    Observable.concat(observable1, observable2)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { printf("Received: $it") },
            { error -> error.printStackTrace() },
            { printf("All events received") })

    //Case 2
    val single1 = Single.just("Single1")
    val single2 = Single.just("Single2")
    val combinedObservable = Observable.concat(
        single1.toObservable(),
        single2.toObservable()
    )
    combinedObservable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
        .subscribe({ item -> printf(item) }, { error -> error.printStackTrace() })
}


/**
 * delay: Trì hoãn việc phát ra các giá trị từ một luồng dữ liệu.
 * Sử dụng với Observable: Trì hoãn việc phát ra các giá trị từ một luồng dữ liệu.
 * Sử dụng với Single: Trì hoãn việc phát ra giá trị từ một Single.
 * Observable: Sử dụng delay để trì hoãn việc phát ra mỗi phần tử trong một chuỗi phát ra nhiều giá trị.
 * Single: Sử dụng delay để trì hoãn việc phát ra giá trị duy nhất.
 * Completable và Maybe: Toán tử delay cũng có thể được sử dụng với Completable và Maybe theo cách tương tự, để trì hoãn việc hoàn thành hoặc phát ra giá trị.
 */
fun performObservableDelay() {
    printf("------Observable delay------")
    val observable = Observable.just("A1", "A2", "A3")
    observable.delay(1, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { printf("Received: $it") },
            { error -> error.printStackTrace() },
            { printf("All events received") })
}

/**
 * filter: Lọc các item theo điều kiện cho trước.
 * Observable: Sử dụng filter để chỉ lấy những phần tử thỏa mãn điều kiện nhất định từ một luồng phát ra nhiều giá trị.
 * Single: Sử dụng filter để chấp nhận hoặc loại bỏ giá trị duy nhất phát ra, dẫn đến việc phát ra lỗi nếu không thỏa mãn điều kiện.
 * Flowable: Sử dụng filter để lọc các phần tử từ một luồng phát ra nhiều giá trị với khả năng kiểm soát ngược áp lực (backpressure).
 */
fun performObservableFilter() {
    printf("------Observable filter------")
    val observable = Observable.just(1, 2, 3, 4, 5, 6)
    observable.filter { it % 2 == 0 } // Lọc các số chẵn
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { printf("Received: $it") },
            { error -> error.printStackTrace() },
            { printf("All events received") })
}

/**
 * Trong RxJava, toán tử merge được sử dụng để kết hợp nhiều Observable vào một Observable duy nhất,
 * phát ra các phần tử từ tất cả các Observable đầu vào xen kẽ nhau ngay khi chúng phát ra giá trị.
 * Không giống như concat, merge không đợi Observable trước đó hoàn thành trước khi phát ra giá trị từ Observable tiếp theo.
 * Điều này hữu ích khi bạn muốn kết hợp các luồng dữ liệu không đồng bộ hoặc thời gian thực.
 * merge: Kết hợp nhiều luồng Observable vào một Observable duy nhất, phát ra giá trị từ tất cả các Observable đầu vào xen kẽ nhau.
 * Sử dụng với Observable: Kết hợp nhiều Observable để phát ra các giá trị từ cả hai một cách không đồng bộ.
 * Sử dụng với Single: Chuyển đổi Single thành Observable để có thể sử dụng merge.
 */
fun performObservableMerge() {
    val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS).take(3)
    val observable2 = Observable.interval(800, TimeUnit.MILLISECONDS).take(5)
    Observable.merge(observable1, observable2)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { printf("Received: $it") },
            { error -> error.printStackTrace() },
            { printf("All events received") })
}


fun performObservableZip() {
    val observable1 = Observable.just("A1", "A2", "A3")
    val observable2 = Observable.just("B1", "B2", "B3", "B4")
    val observable3 = Observable.just("C1", "C2", "C3", "C4")
    Observable.zip(observable1, observable2, observable3) { item1, item2, item3 -> taskDemoForZip(item1, item2, item3) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .toList()
        .subscribe(
            { value ->
                printf("Received: $value")
            },
            { error ->
                error.printStackTrace()
            },
            /*{
                printf("All events received")
            }*/
        )
}

fun taskDemoForZip(vararg value: String): Triple<String, String, String> {
    Thread.sleep(1000)
    value.forEach {
        return Triple(it, it, it)
    }
    return Triple("", "", "")
}


/*
     * Here we are getting two user list
     * One, the list of cricket fans
     * Another one, the list of football fans
     * Then we are finding the list of users who loves both
     */


data class ApiUser(
    var id: Long = 0L,
    var firstname: String,
    var lastname: String
)

data class User(
    var id: Long = 0L,
    var firstname: String,
    var lastname: String,
    var isFollowing: Boolean = false
)


private fun doSomeWork() {
    Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
        BiFunction<List<User>, List<User>, List<User>> { cricketFans, footballFans ->
            return@BiFunction filterUserWhoLovesBoth(cricketFans, footballFans)
        })
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(Schedulers.newThread())
        .subscribe(getObserver())
}

private fun getCricketFansObservable(): Observable<List<User>> {
    return Observable.create { e ->
        if (!e.isDisposed) {
            e.onNext(getUserListWhoLovesCricket())
            e.onComplete()
        }
    }.subscribeOn(Schedulers.io())
}

private fun getFootballFansObservable(): Observable<List<User>> {
    return Observable.create(ObservableOnSubscribe<List<User>> { e ->
        if (!e.isDisposed) {
            e.onNext(getUserListWhoLovesFootball())
            e.onComplete()
        }
    }).subscribeOn(Schedulers.io())
}


fun getApiUserList(): List<ApiUser> {
    val apiUserList = ArrayList<ApiUser>()
    val apiUserOne = ApiUser(firstname = "Amit", lastname = "Shekhar")
    apiUserList.add(apiUserOne)
    val apiUserTwo = ApiUser(firstname = "Janishar", lastname = "Ali")
    apiUserList.add(apiUserTwo)
    val apiUserThree = ApiUser(firstname = "Anand", lastname = "Gaurav")
    apiUserList.add(apiUserThree)
    return apiUserList
}


fun getUserListWhoLovesCricket(): List<User> {
    val userList = ArrayList<User>()
    val userOne = User(id = 1, firstname = "Amit", lastname = "Shekhar")
    userList.add(userOne)
    val userTwo = User(id = 2, firstname = "Janishar", lastname = "Ali")
    userList.add(userTwo)
    return userList
}


fun getUserListWhoLovesFootball(): List<User> {
    val userList = ArrayList<User>()
    val userOne = User(id = 1, firstname = "Amit", lastname = "Shekhar")
    userList.add(userOne)
    val userTwo = User(id = 3, firstname = "Janishar", lastname = "Ali")
    userList.add(userTwo)
    return userList
}

fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {
    val userList = ArrayList<User>()
    for (apiUser in apiUserList) {
        val user = User(apiUser.id, apiUser.firstname, apiUser.lastname)
        userList.add(user)
    }
    return userList
}

fun filterUserWhoLovesBoth(cricketFans: List<User>, footballFans: List<User>): List<User> {
    val userWhoLovesBoth = ArrayList<User>()

    for (footballFan in footballFans) {
        if (cricketFans.contains(footballFan)) {
            userWhoLovesBoth.add(footballFan)
        }
    }
    return userWhoLovesBoth
}
