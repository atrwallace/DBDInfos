package br.com.dbdinfos.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dbdinfos.data.MainDTO
import br.com.dbdinfos.data.MainRepository
import br.com.dbdinfos.util.FirebaseConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    val allPerks = MutableLiveData<List<MainDTO>>()
    val deletionTable = MutableLiveData<Unit>()

    fun getAllPerks() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = dbdrepo.getPerks()
            allPerks.postValue(result)
            dbdrepo.savePerks(result)
        }
    }
    fun delete(){
        CoroutineScope(Dispatchers.IO).launch {
            dbdrepo.delete()
        }
    }
    fun getLocal(){

        CoroutineScope(Dispatchers.IO).launch {
            val result = dbdrepo.getPerkLocal()
            allPerks.postValue(result)
        }
    }

    fun logout() {
        val fire = FirebaseConfig().fireInstance()
        fire.signOut()
    }
}