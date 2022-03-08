package br.com.dbdinfos.dao


import androidx.room.*
import br.com.dbdinfos.data.MainDTODBLocal

@Dao
interface MainDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(mainDTO: List<MainDTODBLocal>)

    @Delete
    suspend fun remove(dTODbLocal: MainDTODBLocal)

    @Update
    suspend fun update(dTODbLocal: MainDTODBLocal)

    @Query("SELECT * FROM table_perks")
    suspend fun getAll() : List<MainDTODBLocal>

    @Query("DELETE FROM table_perks")
    suspend fun delete()
}