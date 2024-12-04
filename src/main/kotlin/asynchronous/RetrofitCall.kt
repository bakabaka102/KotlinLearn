package asynchronous

import helpers.printf
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit.RetrofitObj
import retrofit.RetrofitObjRxJava
import retrofit.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun callNormal() {
    printf("------Base retrofit------")
    RetrofitObj.apiService.getUsersOriginRetrofit().enqueue(object : Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            if (response.isSuccessful) {
                printf("override fun onResponse - call: $call")
                printf("override fun onResponse - response: ${response.body()}")
            } else {
                printf("override fun onResponse - Lỗi rồi")
            }
        }

        override fun onFailure(p0: Call<List<User>>, p1: Throwable) {
            p1.printStackTrace()
        }
    })

    val users = RetrofitObj.apiService.getUsersOriginRetrofit().execute().also {
        printf("execute: ${it.body()}")
    }.body() ?: emptyList<List<User>>()
}

fun callByRxJava() {
    printf("------Rx retrofit------")
    RetrofitObjRxJava.apiService.getUsers()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.newThread())
        .subscribe(
            { data ->
                printf("fun subscribe: $data")
            }, { throwable ->
                throwable.printStackTrace()
            }
        )
}