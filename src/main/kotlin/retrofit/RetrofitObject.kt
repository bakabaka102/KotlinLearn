package retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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

    private val retrofit: Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
        .baseUrl("https://api.chucknorris.io/").client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}

interface ApiService {

    @GET("jokes/random")
    fun randomData(): Call<RandomObject>

    @GET("jokes/random")
    fun singleRandomData(): Single<RandomObject>

}

@Serializable
data class RandomObject(
    @SerialName("categories")
    var categories: ArrayList<String> = arrayListOf(),
    @SerialName("created_at")
    var createdAt: String? = null,
    @SerialName("icon_url")
    var iconUrl: String? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("updated_at")
    var updatedAt: String? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("value")
    var value: String? = null
)