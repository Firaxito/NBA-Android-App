package eu.petrfaruzel.nba.data.api

import android.provider.Telephony.Carriers.SERVER
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import eu.petrfaruzel.nba.shared.NETWORK_CONFIG
import io.ktor.websocket.Serializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

object RetrofitHelper {

    inline fun <reified T> provideApi(
        retrofit: Retrofit
    )  :T {
        return retrofit.newBuilder()
            .baseUrl(NETWORK_CONFIG.SERVER_URL)
            .build()
            .create(T::class.java)
    }

    fun provideRetrofit() : Retrofit {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.readTimeout(
            NETWORK_CONFIG.SERVER_TIMEOUT_READ_SEC,
            TimeUnit.SECONDS
        )

        clientBuilder.writeTimeout(
            NETWORK_CONFIG.SERVER_TIMEOUT_WRITE_SEC,
            TimeUnit.SECONDS
        )

        clientBuilder.connectTimeout(
            NETWORK_CONFIG.SERVER_TIMEOUT_CONNECT_SEC,
            TimeUnit.SECONDS
        )

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.interceptors().add(logging)

        val gson = GsonBuilder().setLenient()

        return Retrofit.Builder()
            .baseUrl(NETWORK_CONFIG.SERVER_URL)
            .client(clientBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson.create()))
            .build()
    }
}