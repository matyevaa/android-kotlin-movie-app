package com.example.movietime.data

data class Genre (
    var id: Int,
    var name: String
    )

fun List<Genre>.toIDList(): MutableList<Int> {
    val idList =  mutableListOf<Int>()
    for (i in this.indices) {
        idList.add(this[i].id)
    }
    return idList
}

fun List<Genre>.toNameList(): MutableList<String> {
    val nameList =  mutableListOf<String>()
    for (i in this.indices) {
        nameList.add(this[i].name)
    }
    return nameList
}