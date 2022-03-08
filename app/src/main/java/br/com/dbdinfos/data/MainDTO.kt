package br.com.dbdinfos.data

data class MainDTO(
    val _id: String,
    val description: String,
    val icon: String,
    val is_ptb: Boolean,
    val lang: String,
    val name: String,
    val name_tag: String,
    val perk_name: String,
    val perk_tag: String,
    val role: String,
    val teach_level: Int
)