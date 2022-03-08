package br.com.dbdinfos.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "table_perks", indices = [Index(value = ["_id"], unique = true )])
data class MainDTODBLocal(
    @PrimaryKey
    val _id: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "icon")
    val icon: String,
    @ColumnInfo(name = "is_ptb")
    val is_ptb: Boolean,
    @ColumnInfo(name = "lang")
    val lang: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "name_tag")
    val name_tag: String,
    @ColumnInfo(name = "perk_name")
    val perk_name: String,
    @ColumnInfo(name = "perk_tag")
    val perk_tag: String,
    @ColumnInfo(name = "role")
    val role: String,
    @ColumnInfo(name = "tech_level")
    val teach_level: Int
)