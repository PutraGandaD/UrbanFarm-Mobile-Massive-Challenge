package com.putragandad.urbanfarm.api

import com.putragandad.urbanfarm.models.api.JualPanenModel
import com.putragandad.urbanfarm.models.api.ResponseModel
import com.putragandad.urbanfarm.models.api.VideosDashboardModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiEndpoint {
    @GET("api/v1/jualpanen")
    @Headers("Content-Type: application/json")
    fun data(): Call<JualPanenModel>

    @GET("api/v1/videos")
    @Headers("Content-Type: application/json")
    fun getVideos(): Call<VideosDashboardModel>

    @GET("api/v1/jualpanen/users/{id_user}")
    @Headers("Content-Type: application/json")
    fun dataByIdUser(
        @Path("id_user") id_user: String,
    ): Call<JualPanenModel>

    @FormUrlEncoded
    @POST("api/v1/jualpanen")
    fun create(
        @Field("id_user") idUser: String,
        @Field("profileimg_url") profileimg_url: String,
        @Field("username") username: String,
        @Field("whatsapp_no") whatsapp_no: String,
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("contentimg_url") contentimg_url: String,
        @Field("kota") kota: String,
        @Field("jenisTanaman") jenisTanaman: String,
        @Field("metodeTanam") metodeTanam: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @PUT("api/v1/jualpanen/{id}")
    fun update(
        @Path("id") id: String,
        @Field("id_user") idUser: String,
        @Field("profileimg_url") profileimg_url: String,
        @Field("username") username: String,
        @Field("whatsapp_no") whatsapp_no: String,
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("contentimg_url") contentimg_url: String,
        @Field("kota") kota: String,
        @Field("jenisTanaman") jenisTanaman: String,
        @Field("metodeTanam") metodeTanam: String
    ): Call<ResponseModel>

}