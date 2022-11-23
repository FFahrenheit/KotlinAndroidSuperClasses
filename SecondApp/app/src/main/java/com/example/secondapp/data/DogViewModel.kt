package com.example.secondapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogViewModel : ViewModel() {
    private var dogList : MutableLiveData<ArrayList<DogModel>>? = null

    fun selectItem(data : ArrayList<DogModel>){
        this.dogList!!.value = data
    }

    fun getItem() : MutableLiveData<ArrayList<DogModel>>{
        if(dogList == null){
            dogList = MutableLiveData()
            dogList!!.value = ArrayList()
        }
        return dogList!!
    }

    fun addDog(dog : DogModel){
        dogList!!.value!!.add(dog)
    }

}