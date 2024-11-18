package hu.bme.aut.httpdemo.network

import hu.bme.aut.httpdemo.data.money.MoneyResult
import retrofit2.http.GET

// https://api.exchangerate-api.com/v4/latest/USD

//Host: https://api.exchangerate-api.com/
//Path: v4/latest/USD
//Query params: we do not have now...

interface MoneyAPI {

    @GET("v4/latest/USD")
    suspend fun getRates(): MoneyResult

}