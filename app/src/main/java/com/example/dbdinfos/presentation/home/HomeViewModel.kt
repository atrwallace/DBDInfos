package com.example.dbdinfos.presentation.home

import android.content.ClipData
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbdinfos.data.MainDTO
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.util.FirebaseConfig
import kotlinx.coroutines.launch

class HomeViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    val killerperk = MutableLiveData<List<MainDTO>>()
    val survperk = MutableLiveData<List<MainDTO>>()

    fun setPerksKiller() {
        viewModelScope.launch {
            val result = dbdrepo.getPerks().filter {
                it.role == "Killer"
            }
            killerperk.value = result

        }
    }

    fun setPerksSurv() {
        viewModelScope.launch {
            val result = dbdrepo.getPerks().filter {
                it.role == "Survivor"
            }
            survperk.value = result
        }
    }

    fun clearList() {
        killerperk.value = emptyList()
        survperk.value = emptyList()
    }
    fun logout(){
        val fire = FirebaseConfig().fireInstance()
        fire.signOut()
    }
}