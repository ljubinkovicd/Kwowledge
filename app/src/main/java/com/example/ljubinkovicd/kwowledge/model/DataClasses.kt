package com.example.ljubinkovicd.kwowledge.model

/**
 * Created by ljubinkovicd on 8/20/17.
 */

data class Boss(val name: String, val description: String?, val health: Int, val npcs: List<Npc>)

data class Npc(val id: Int, val name: String, val urlSlug: String, val creatureDisplayId: Int) {

    override fun toString(): String {
        return "$id, $name, $urlSlug, $creatureDisplayId"
    }
}