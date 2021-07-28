package com.mycompany.weather.model

import com.mycompany.weather.model.Repository
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MyModel(citiesArray: Array<City>) : Repository {
    private var city: City? = null

    private var cities: List<City> = citiesArray.toList()

    override fun changeCity(name: String) {
        city = cities.find { city -> city.name == name }
    }


    override fun getArrayCities(): Array<City> {
        return cities.toTypedArray()
    }

    private fun requestGet(city: City?) {
        val client = OkHttpClient()

        val request = Request.Builder().url(
            BuilderURL().setURL(URI)
                .addParam(City::lat.name, city?.lat.toString())
                .addParam(City::lon.name, city?.lon.toString())
                .build()
        ).header(HEADER_NAME, KEY).build()

        var string = "lol"
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    string = response.body!!.string()
                }
            }
        })
        Thread.sleep(1000)

        city?.degree = JSONObject(string).getJSONObject("fact").getInt("temp")
    }

    override fun requestGet() {
        requestGet(city)

    }

    override fun getSelectedCity(): City? {
        return city
    }

    override fun findACityByGeo() {
    }

}

private const val URI = "https://api.weather.yandex.ru/v2/forecast"
private const val HEADER_NAME = "X-Yandex-API-Key"
private const val KEY = "2aaf6307-4662-40d1-a590-edc3524b5d1e"

