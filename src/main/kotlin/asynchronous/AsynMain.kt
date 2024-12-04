package asynchronous

import helpers.printf
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.system.measureTimeMillis

val compositeDisposable = CompositeDisposable()

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun main() {
    val timeExecuteTask = measureTimeMillis {
        //single.demoSingle()
        //just.demoSingle()

        //performSingleJust()

        //performObservableConcat()
        //performObservableDelay()
        //performObservableFilter()
        //performObservableMerge()
        performObservableZip()

        //performFlowableFilter()
        //performFlowableMap()

        //performSingleConcat()
        //performSingleFilter()
        //performSingleMerge()
    }.also {
        printf("Time execute task: $it")
    }
    (timeExecuteTask + 5000).also {
        Thread.sleep(it).also {
            compositeDisposable.clear()
            compositeDisposable.dispose()
        }
        printf("Time waiting: $it")
    }
}
