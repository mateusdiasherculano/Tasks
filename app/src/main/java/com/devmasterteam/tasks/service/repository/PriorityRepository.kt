package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository (val context: Context) {


    private val remote = RetrofitClient.getService(PriorityService :: class.java)
    private val priorityDatabase = TaskDatabase.getDatabase(context).priorityDAO()

    fun list(listener: APIListener<List<PriorityModel>>){
        val call = remote.list()
        call.enqueue(object : Callback<List<PriorityModel>>{
            override fun onResponse(
                call: Call<List<PriorityModel>>,
                response: Response<List<PriorityModel>>
            ) {
                if (response.code() == TaskConstants.HTTP.SUCCESS){
                    response.body()?.let { listener.onSuccess(it) }
                }else{
                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun list(): List<PriorityModel>{
        return priorityDatabase.list()
    }

    fun save(list: List<PriorityModel>){
        priorityDatabase.clear()
        priorityDatabase.save(list)
    }

    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }
}