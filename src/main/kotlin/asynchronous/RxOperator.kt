package asynchronous

import helpers.printf
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun getObserver(): Observer<Any> {
    return object : Observer<Any> {

        override fun onSubscribe(d: Disposable) {
            printf("onSubscribe : " + d.isDisposed)
        }

        override fun onNext(value: Any) {
            printf("onNext : value : $value")
        }

        override fun onError(e: Throwable) {
            printf("onError : ${e.message}")
        }

        override fun onComplete() {
            printf("onComplete")
        }
    }
}