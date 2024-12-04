package asynchronous

import helpers.printf
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

fun performCompletable() {
    printf("------Completable------")
    /*val completable = Completable.create { emitter ->
        emitter.onComplete()
    }*/
    val completable = performTask()
    completable.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io()).subscribe(
            {
                printf("onComplete")
            }, {
                printf("onError")
            })
}

fun performTask(): Completable {
    return Completable.fromAction {
        // Thực hiện tác vụ
        printf("Task is complete")
    }
}

