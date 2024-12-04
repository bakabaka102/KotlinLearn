package retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.GET

object RetrofitObjRxJava {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    private val retrofit: Retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(json.asConverterFactory(contentType))
        .baseUrl("https://api.chucknorris.io/")
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}

object RetrofitObj {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    private val retrofit: Retrofit = Retrofit.Builder()/*.addCallAdapterFactory(RxJava3CallAdapterFactory.create())*/
        .addConverterFactory(json.asConverterFactory(contentType))
        .baseUrl(ApiConstants.BASE_URL).client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}

interface ApiService {

    @GET("users/")
    fun getUsersOriginRetrofit(): Call<List<User>>

    @GET("users/")
    fun getUsers(): Single<List<User>>

    @GET("users/{id}")
    fun getUserById(): Single<User>

}