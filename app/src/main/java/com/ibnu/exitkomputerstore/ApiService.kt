package com.ibnu.exitkomputerstore

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    var BASE_URL: String = "http://10.10.13.25/exitKomputer/"

    val endPoint: ApiEndPoint

    get() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiEndPoint::class.java)
    }
}