package com.example.dbdinfos.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbdinfos.data.MainDTO
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.util.FirebaseConfig
import kotlinx.coroutines.launch

class HomeViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    val killerPerk = MutableLiveData<List<MainDTO>>()
    val survPerk = MutableLiveData<List<MainDTO>>()

    fun setPerksKiller() {
        viewModelScope.launch {
            val result = dbdrepo.getPerks().filter {
                it.role == "Killer"
            }
            killerPerk.value = result

        }
    }

    fun setPerksSurv() {
        viewModelScope.launch {
            val result = dbdrepo.getPerks().filter {
                it.role == "Survivor"
            }
            survPerk.value = result
        }
    }
    fun logout(){
        val fire = FirebaseConfig().fireInstance()
        fire.signOut()
    }
}