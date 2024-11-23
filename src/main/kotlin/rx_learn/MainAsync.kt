package asynchronous

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit.RandomObject
import retrofit.RetrofitObj
import retrofit.RetrofitObjRxJava
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val compositeDisposable = CompositeDisposable()

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun main() {

    //collect()

    val single: Single<List<String>> = Single.fromCallable {
        listOf("Item 1", "Item 2", "Item 3")
    }
    val completable = Completable.fromCallable {
        listOf("Item 1", "Item 2", "Item 3")
    }
    val just = Single.just {
        listOf("Item 1", "Item 2", "Item 3")
    }
    /*single.doOnSubscribe {
        //ex: Show loading icon
    }.doOnTerminate {
        //ex: Turn off loading icon
    }
        .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        .subscribe(
            { result ->
                println("Received list: $result")
            },
            { error ->

            }).addToCompositeDisposable(compositeDisposable)

    completable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe({

    }, {

    }).addToCompositeDisposable(compositeDisposable)*/

//    observableConcat()
//    observableJust()

    /*println("------Base retrofit------")
    RetrofitObj.apiService.randomData().enqueue(object : Callback<RandomObject> {
        override fun onResponse(call: Call<RandomObject>, response: Response<RandomObject>) {
            if (response.isSuccessful) {
                println("override fun onResponse - call: $call")
                println("override fun onResponse - response: ${response.body()}")
            } else {
                println("override fun onResponse - Lỗi rồi")
            }
        }

        override fun onFailure(p0: Call<RandomObject>, p1: Throwable) {
            p1.printStackTrace()
        }
    })*/
    println("------Rx retrofit------")
    RetrofitObjRxJava.apiService.singleRandomData()
//        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.newThread())
        .subscribe({ data ->
            println("subscribe: $data")
        }, { throwable ->
            throwable.printStackTrace()
        }).addToCompositeDisposable(compositeDisposable)
    //compositeDisposable.clear()
    //compositeDisposable.dispose()
}

private fun observableConcat() {
    println("------observableConcat-------")
    val observableA = Observable.fromArray("A1", "A2", "A3", "A4")
    val observableB = Observable.fromArray("B1", "B2", "B3", "B4")
    Observable.concat(observableA, observableB).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        .subscribe(getObserver())
}

private fun observableJust() {
    println("---------observableJust----------")
    val letters = listOf("aa", "bb", "cc", "dd", "ee", "ff", "gg")
    val observable = Observable.just(letters)
    observable.subscribe(getObserver())
}

private fun getObserver(): Observer<Any> {
    return object : Observer<Any> {

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe : " + d.isDisposed)
        }

        override fun onNext(value: Any) {
            println("onNext : value : $value")
        }

        override fun onError(e: Throwable) {
            println("onError : ${e.message}")
        }

        override fun onComplete() {
            println("onComplete")
        }
    }
}

private fun collect() {
    val numbersSequence = sequenceOf("four", "three", "two", "one")
    val numbers = listOf("one", "two", "three", "four")
    val numbersC = numbers.asSequence()
    val oddNumbers = generateSequence(1) { it + 2 }
    println(oddNumbers.take(5).toList())
}