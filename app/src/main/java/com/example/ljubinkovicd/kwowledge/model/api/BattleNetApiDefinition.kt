package com.example.ljubinkovicd.kwowledge.model.api

import com.example.ljubinkovicd.kwowledge.model.Boss
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable
import java.util.*

/**
 * Created by ljubinkovicd on 8/20/17.
 */

interface BattleNetApiDefinition {

    // TODO: Each Retrofit annotation NEEDS to have '/' at the end to simplify sending requests.
    @GET("boss/")
    fun listBosses(
            @Query("locale") locale: Locale,
            @Query("apiKey") apiKey: String
    ) : Observable<BossResult>

    @GET("boss/{bossId}/")
    fun loadBoss(@Path("bossId") bossId : String) : Observable<Boss>
}