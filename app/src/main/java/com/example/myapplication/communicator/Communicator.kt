package com.example.myapplication.communicator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel(){

    val message = MutableLiveData<Any>()

    fun setMsgCommunicator(msg:String){
        message.setValue(msg)
    }

}