package com.novandi.premierleaguematchday.model

data class Club(
    val id: Int,
    val name: String,
    val stadium: String,
    val logoUrl: String,
    val color: Int,
    val squad: Squad,
    val matchday: List<List<String>>,
    var isFavorite: Boolean = false
)