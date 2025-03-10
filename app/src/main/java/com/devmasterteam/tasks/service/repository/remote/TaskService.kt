package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskService {

    @GET("Task")
    fun list(): Call<List<TaskModel>>
    @GET("Next7Days")
    fun listNextSevenDays(): Call<List<TaskModel>>
    @GET("OverDue")
    fun listOverDue(): Call<List<TaskModel>>

    @GET("Task/{id}")
    fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded
    fun create(
        @Field("PriorityId") priorityId: Int,
        @Field("PriorityId") description: String,
        @Field("PriorityId") dueDate: String,
        @Field("PriorityId") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task")
    @FormUrlEncoded
    fun update(
        @Field("Id") id: Int,
        @Field("PriorityId") priorityId: Int,
        @Field("PriorityId") description: String,
        @Field("PriorityId") dueDate: String,
        @Field("PriorityId") complete: Boolean,
    ): Call<Boolean>

    @PUT("Task/Complete")
    @FormUrlEncoded
    fun complete(@Field("Id") id: Int): Call<Boolean>
    @PUT("Task/Undo")
    @FormUrlEncoded
    fun undo(@Field("Id") id: Int): Call<Boolean>

    @DELETE("Task")
    @FormUrlEncoded
    fun delete(@Field("Id") id: Int): Call<Boolean>
}