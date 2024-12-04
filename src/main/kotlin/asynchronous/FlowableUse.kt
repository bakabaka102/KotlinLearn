package asynchronous

import helpers.printf
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers


fun performFlowableFilter() {
    // Tạo một Flowable
    val flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // Lọc các item
    flowable.filter { it % 2 == 0 }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())/*.toList()*/
        .subscribe(
            { received -> printf("Received: $received") },
            { error -> error.printStackTrace() }, {
                printf("All events received")
            }
            /*compositeDisposable*/
        )
}

fun performFlowableMap() {
    // Tạo một Flowable
    val flowable = Flowable.just(1, 2, 3, 4, 5, 6)
    // Ánh xạ các item
    flowable.map { it * 2 }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(
            { printf("Received: $it") },
            { error -> error.printStackTrace() },
            { printf("All events received") })
}