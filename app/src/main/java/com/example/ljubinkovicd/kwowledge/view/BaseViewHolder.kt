package com.example.ljubinkovicd.kwowledge.view

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by ljubinkovicd on 8/21/17.
 */
abstract class BaseViewHolder<D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: D)
}