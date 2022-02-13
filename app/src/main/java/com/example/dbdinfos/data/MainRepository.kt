package com.example.dbdinfos.data

interface MainRepository {
    suspend fun getPerks(): List<MainDTO>
}

class MainRepositoryImpl(private val service: DbdService) : MainRepository {

    override suspend fun getPerks(): List<MainDTO> {
        try {
            return service.getPerks()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return listOf()
    }
}