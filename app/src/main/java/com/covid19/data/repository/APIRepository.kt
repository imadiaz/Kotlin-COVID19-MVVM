package com.covid19.data.repository

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val SERVER_URL = "https://api.covid19api.com/"
interface APIRepository {

    @GET("summary")
    //Deferred its a courtines to await the response
   suspend fun getSummary(): Response<GlobalSummary>

    @GET("dayone/country/{country}")
    suspend fun getCountryStatus(
        @Path("country") country:String
    ):Response<List<CountryStatus>>


    companion object {
        operator fun invoke():APIRepository{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build()
                .create(APIRepository::class.java)
        }
    }

}