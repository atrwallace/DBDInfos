package br.com.dbdinfos.data

import br.com.dbdinfos.dao.MainDAO

interface MainRepository {
    suspend fun getPerks(): List<MainDTO>
    suspend fun getPerkLocal(): List<MainDTO>
    suspend fun savePerks(mainDTO: List<MainDTO>)
    suspend fun delete()
}

class MainRepositoryImpl(private val service: DbdService, private val dao: MainDAO) :
    MainRepository {

    override suspend fun getPerks(): List<MainDTO> {
        try {
            return service.getPerks()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return listOf()
    }


    override suspend fun getPerkLocal(): List<MainDTO> {
        val list = mutableListOf<MainDTO>()
        try {
            val localList = dao.getAll()
            localList.forEach { localList ->
                val mainDTO = MainDTO(
                    _id = localList._id,
                    description = localList.description,
                    icon = localList.icon,
                    is_ptb = localList.is_ptb,
                    lang = localList.lang,
                    name = localList.name,
                    name_tag = localList.name_tag,
                    perk_name = localList.perk_name,
                    perk_tag = localList.perk_tag,
                    role = localList.role,
                    teach_level = localList.teach_level
                )
                list.add(mainDTO)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    override suspend fun savePerks(dbLocal: List<MainDTO>) {

        val list = mutableListOf<MainDTODBLocal>()

        dbLocal.forEach { dbLocal ->
            val mainDTODBLocal = MainDTODBLocal(
                _id = dbLocal._id,
                description = dbLocal.description,
                icon = dbLocal.icon,
                is_ptb = dbLocal.is_ptb,
                lang = dbLocal.lang,
                name = dbLocal.name,
                name_tag = dbLocal.name_tag,
                perk_name = dbLocal.perk_name,
                perk_tag = dbLocal.perk_tag,
                role = dbLocal.role,
                teach_level = dbLocal.teach_level
            )
            list.add(mainDTODBLocal)
        }
        dao.save(
            list
        )
    }

    override suspend fun delete() {
        dao.delete()
    }

}