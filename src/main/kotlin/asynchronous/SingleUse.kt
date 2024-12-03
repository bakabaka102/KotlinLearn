package asynchronous

import helpers.printf
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


fun performSingleJust() {
    printf("---------observableJust----------")
    val letters = listOf("aa", "bb", "cc", "dd", "ee", "ff", "gg")
    Single.just(letters).subscribe({ printf("Received: $it") }, { printf("Error: $it") })
}

/**
 * Trong RxJava, Single.concat không tồn tại trực tiếp như một phương thức của Single.
 * Tuy nhiên, bạn có thể sử dụng Single.concatWith để nối kết các Single.
 * Điều này sẽ kết hợp hai Single và phát ra các giá trị của chúng theo thứ tự.
 */
fun performSingleConcat() {
    val single1 = Single.just("Single1")
    val single2 = Single.just("Single2")
    val concatWith: Flowable<String> = single1.concatWith(single2)
    concatWith.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { item ->
                printf("use concatWith - Received: $item")
            },
            { error ->
                error.printStackTrace()
            }
        )
    val combinedObservable: Flowable<String> = Single.concat(
        single1,
        single2
    )
    combinedObservable.subscribe { /*item -> printf(item)*/
        getObserver()
    }
}

/**
 * Bạn cũng có thể sử dụng filter với Single, mặc dù việc này ít phổ biến hơn vì Single chỉ phát ra một giá trị duy nhất.
 * Trong trường hợp này, nếu giá trị không thỏa mãn điều kiện lọc, nó sẽ không phát ra giá trị nào và phát ra một lỗi NoSuchElementException
 */
fun performSingleFilter() {
    printf("------Single filter------")
    val single = Single.just(2)
    single.filter { it % 2 == 0 }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { item ->
                //Handle data
                printf("Received: $item")
            },
            { error ->
                //Handle error
                error.printStackTrace()
            }, {

            }
        )
}

fun performSingleMerge() {
    val single1 = Single.just("First Single")
    val single2 = Single.just("Second Single")
    val mergedObservable = Observable.merge(single1.toObservable(), single2.toObservable())
    mergedObservable.subscribe { item -> println("Received: $item") }
}

fun <T : Any> Single<T>.demoSingle() {
    val subscribe = this.doOnSubscribe {
        printf("doOnSubscribe")
    }.doOnTerminate {
        printf("doOnTerminate")
    }
        .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        .subscribe(
            { result ->
                val list = result ?: emptyList<Any>()
                printf("Received list: $list")
            },
            { error ->
                printf("Error: $error")
            })/*.addToCompositeDisposable(compositeDisposable)*/
}
