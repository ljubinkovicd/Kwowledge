package com.example.ljubinkovicd.kwowledge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.ljubinkovicd.kwowledge.adapter.BossListAdapter
import com.example.ljubinkovicd.kwowledge.model.Boss
import com.example.ljubinkovicd.kwowledge.model.api.BattleNetApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    private val rv by lazy { findViewById<RecyclerView>(R.id.boss_list_recycler_view) }
    private val bossAdapter = BossListAdapter()
    var bosses = mutableListOf<Boss>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.adapter = bossAdapter
        rv.layoutManager = LinearLayoutManager(this)

        val api = BattleNetApi()
        api.loadBosses(Locale.US, resources.getString(R.string.client_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ boss ->
                    val description = boss.description ?: "No description for ${boss.name}"
                    bosses.add(Boss(boss.name, description, boss.health, boss.npcs))
                }, {
                    error -> error.printStackTrace()
                }, {
                    bossAdapter.initializeDataSource(bosses)
                    bossAdapter.notifyDataSetChanged()
                })
    }
}