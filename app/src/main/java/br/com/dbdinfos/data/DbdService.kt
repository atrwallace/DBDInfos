package br.com.dbdinfos.data

import retrofit2.http.GET

interface DbdService {

    @GET("/perks")
    suspend fun getPerks() : List<MainDTO>

}