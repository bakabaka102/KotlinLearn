package rx_learn

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ulti.Logger

//Common operator in Rx

fun fromOperator() {
    val observableA = Observable.fromArray("A1", "A2", "A3", "A4")
    val observableB = Observable.fromArray("B1", "B2", "B3", "B4")
    //observableA.subscribeOn(fromObserverObject())
}

//fun fromObserverObject(): Scheduler {
//
//
//}

/*
 * Using concat operator to combine Observable : concat maintain
 * the order of Observable.
 * It will emit all the 7 values in order
 * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
 * first all from the first Observable and then
 * all from the second Observable all in order
 */


fun justOperator() {
    Observable.just(1, 2, 3, 4, 5)
        // Run on a background thread
        /*.subscribeOn(Schedulers.io())*/
        // Be notified on the main thread
        /*.observeOn(Schedulers.io())*/.subscribe(justObserverObject())
}

fun justObserverObject(): Observer<Int> {
    return object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Logger.d(" onSubscribe : " + d.isDisposed)
        }

        override fun onError(e: Throwable) {
            Logger.e(" onError : " + e.message)
        }

        override fun onComplete() {
            Logger.d("onComplete")
        }

        override fun onNext(value: Int) {
            Logger.d(" onNext : value : $value")
        }
    }
}
