package com.example.ljubinkovicd.kwowledge.model.api

import com.example.ljubinkovicd.kwowledge.model.Boss
import com.example.ljubinkovicd.kwowledge.model.Npc
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.*

/**
 * Created by ljubinkovicd on 8/20/17.
 */

class BattleNetApi {

    val BASE_URL = "https://us.api.battle.net/wow/"
    val apiService : BattleNetApiDefinition

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        apiService = retrofit.create<BattleNetApiDefinition>(BattleNetApiDefinition::class.java)
    }

    fun loadBosses(locale: Locale, apiKey: String) : Observable<Boss> {
        return apiService.listBosses(locale, apiKey)
                .flatMap { bossResults -> Observable.from(bossResults.bosses) }
                .flatMap { boss -> Observable.just(Boss(boss.name, boss.description, boss.health, boss.npcs)) }
    }
}