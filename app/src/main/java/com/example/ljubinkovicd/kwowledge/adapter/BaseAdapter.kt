package com.example.ljubinkovicd.kwowledge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ljubinkovicd.kwowledge.view.BaseViewHolder

/**
 * Created by ljubinkovicd on 8/21/17.
 */
abstract class BaseAdapter<D, VH : BaseViewHolder<D>> : RecyclerView.Adapter<VH>() {

    var dataSource: List<D> = emptyList()

    abstract fun getItemViewId() : Int

    abstract fun instantiateViewHolder(view: View?) : VH

    fun getItem(position: Int) = dataSource[position]

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(getItemViewId(), parent, false)

        return instantiateViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.onBind(getItem(position))

    override fun getItemCount() = dataSource.size
}