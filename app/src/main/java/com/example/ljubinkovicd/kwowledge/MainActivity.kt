package com.example.ljubinkovicd.kwowledge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.ljubinkovicd.kwowledge.model.Boss
import com.example.ljubinkovicd.kwowledge.model.api.BattleNetApi
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    var listView : ListView? = null
    var bosses = mutableListOf<Boss>()
    var bossesAdapter : ArrayAdapter<Boss>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView = ListView(this)
        bosses = mutableListOf<Boss>()

        setContentView(listView)

        bossesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bosses)
        listView?.adapter = bossesAdapter

        val api = BattleNetApi()
        api.loadBosses(Locale.US, resources.getString(R.string.client_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    boss -> bosses.add(Boss(boss.name, boss.description, boss.health, boss.npcs))
                }, {
                    error -> error.printStackTrace()
                }, {
                    bossesAdapter?.notifyDataSetChanged()
                })
    }
}