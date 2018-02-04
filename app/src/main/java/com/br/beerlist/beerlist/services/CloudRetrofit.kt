package com.br.beerlist.beerlist.services

import android.content.Context
import com.br.beerlist.beerlist.BuildConfigApp
import com.br.beerlist.beerlist.api.PunkBeersApi
import com.br.beerlist.beerlist.di.Injector
import com.br.beerlist.beerlist.utils.NetworkManager
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Provides
import io.realm.RealmObject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Inject
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by jonathan on 03/02/2018.
 */
class CloudRetrofit {

    @Inject
    lateinit var context: Context

    init {
        Injector.component.inject(this)
    }

    fun netWorkManager() : NetworkManager {
        return NetworkManager(context)
    }

    private fun removeRealmParams(): Gson {
        return GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        }).create()
    }

    private fun configureRetrofitSecureRequest() : OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                        chain: Array<java.security.cert.X509Certificate>,
                        authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                        chain: Array<java.security.cert.X509Certificate>,
                        authType: String) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts,
                    java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext
                    .getSocketFactory()

            var okHttpClient = OkHttpClient()
            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build()

            return okHttpClient
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    fun getApiInstance(): PunkBeersApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfigApp.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(removeRealmParams()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(configureRetrofitSecureRequest())
                .build()
        return retrofit.create(PunkBeersApi::class.java)
    }

}