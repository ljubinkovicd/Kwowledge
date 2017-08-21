package com.example.ljubinkovicd.kwowledge.model.api

import com.example.ljubinkovicd.kwowledge.model.Npc
import com.google.gson.annotations.SerializedName

/**
 * Created by ljubinkovicd on 8/20/17.
 */

data class BossResult(val bosses: List<BossResponse>) // Parameter has to match the name of array/element from JSON

data class BossResponse(
        @SerializedName("name")
        val name: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("health")
        val health: Int,

        @SerializedName("npcs")
        val npcs: List<Npc>
)