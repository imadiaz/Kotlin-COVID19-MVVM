package com.covid19

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.covid19.data.repository.APIRepository
import com.covid19.data.repository.GlobalSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _summary = MutableLiveData<GlobalSummary>()
    val summary:LiveData<GlobalSummary>
        get() = this._summary

    fun onLoad(){
        this.getSummary()
    }
    private fun getSummary(){
        val apiRepository = APIRepository()
        GlobalScope.launch (Dispatchers.Main){
           val response =  apiRepository.getSummary()
            if(response.isSuccessful){
               // Log.w("Fetch","Data ${response.body() as GlobalSummary}")
                _summary.postValue(response.body())
            }
        }
    }
}