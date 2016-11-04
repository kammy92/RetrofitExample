package com.karman.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Belal on 11/3/2015.
 */
public interface ServicesAPI {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @FormUrlEncoded
    @POST("/getallservices.php")
//    public void getServices (
//            @Field("city_id") String city_id,
//            @Field("locality_id") String locality_id,
//            Callback<List<Services>> response);

    public void getServices (
            @Field("city_id") String city_id,
            @Field("locality_id") String locality_id,
            Callback<Response> response);
}
