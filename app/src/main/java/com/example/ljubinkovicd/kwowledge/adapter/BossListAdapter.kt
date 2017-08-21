package com.example.ljubinkovicd.kwowledge.adapter

import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.ljubinkovicd.kwowledge.R
import com.example.ljubinkovicd.kwowledge.model.Boss
import com.example.ljubinkovicd.kwowledge.view.BaseViewHolder
import kotlinx.android.synthetic.main.boss_list_item.view.*

/**
 * Created by ljubinkovicd on 8/21/17.
 */

class BossListAdapter : BaseAdapter<Boss, BossListAdapter.BossViewHolder>() {

    override fun getItemViewId() = R.layout.boss_list_item

    override fun instantiateViewHolder(view: View?) = BossViewHolder(view)

    fun initializeDataSource(newDataSource: MutableList<Boss>) {
        dataSource = newDataSource
    }

    override fun onBindViewHolder(holder: BossViewHolder?, position: Int, payloads: MutableList<Any>?) {

        holder?.itemView?.setOnClickListener {
            Log.d("RV", "CLICKED: ${dataSource[position].npcs}")
            // Pass all the information from the Boss object to BossDetailFragment
//            navigateToDetailFragment(dataSource[position].name, dataSource[position].description, dataSource[position].health, ...)
        }

        super.onBindViewHolder(holder, position, payloads)
    }

    /** Creates a view template to fill each boss item data. */
    class BossViewHolder(itemView: View?) : BaseViewHolder<Boss>(itemView) {

        val bossName by lazy { itemView?.findViewById<TextView?>(R.id.boss_name_text_view) }
        val bossDescription by lazy { itemView?.findViewById<TextView?>(R.id.boss_description_text_view) }
        val bossZoneId by lazy { itemView?.findViewById<TextView?>(R.id.boss_zone_id_text_view) }

        override fun onBind(bossItem: Boss) {

            bossItem.let {
                bossName?.text = bossItem.name
                bossDescription?.text = bossItem.description
                bossZoneId?.text = bossItem.health.toString()
            }
        }
    }
}