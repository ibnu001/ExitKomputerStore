package com.ibnu.exitkomputerstore

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("exitKomputerAPI.php?item=getAllItem")
    fun getItem(): Call<ItemModel>
}