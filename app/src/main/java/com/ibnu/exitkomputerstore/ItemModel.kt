package com.ibnu.exitkomputerstore

data class ItemModel(
    var result: ArrayList<Result>
) {
    data class Result(
        val id: Int,
        val nama: String,
        val deskripsi: String,
        val harga: Int,
        val image: String
    )
}