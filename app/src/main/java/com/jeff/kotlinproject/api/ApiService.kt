package com.rxdemo.jeff.rxdemo.utils.network

import com.alibaba.fastjson.JSONObject
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by .F on 2017/5/3.
 */

interface ApiService {


    @GET("api/login/LoginFuc")
    fun login(@Query("flag") flag: Int, @Query("username") name: String, @Query("urlcode") urlcode: String): Observable<JSONObject>

    @Multipart
    @POST("/api/account/UploadImages")
    fun uploadFile(@Part body: MultipartBody.Part): Observable<JSONObject>

    @POST("/api/account/EditUser")
    fun uploadStr(@Body body: RequestBody): Observable<JSONObject>

    @FormUrlEncoded
    @POST("/api/account/EditUser")
    fun uploadStrs(@FieldMap fields: Map<String, String>): Observable<JSONObject>

    @GET("api/login/getimg")
    fun getBannerIMGs(): Observable<JSONObject>
}
