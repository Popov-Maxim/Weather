package com.mycompany.weather.model

import androidx.annotation.WorkerThread
import com.mycompany.weather.room.AppDatabase
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MyModel(citiesArray: Array<City>, private val db: AppDatabase) : MyRepository {
    override var city: City? = null
        private set

    override var cities: List<City> = citiesArray.toList()
        private set

    override fun changeCity(name: String) {
        city = cities.find { city -> city.name == name }
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

        try {
            city?.degree = JSONObject(string).getJSONObject("fact").getInt("temp")
        } catch (e: Exception) {
        }
    }

    override fun requestGet() {
        requestGet(city)
    }

    @WorkerThread
    override fun loadCityFromDatabase() {
        cities = db.cityDao().getAll()
    }

    override fun findACityByGeo() {
    }
}

private const val URI = "https://api.weather.yandex.ru/v2/forecast"
private const val HEADER_NAME = "X-Yandex-API-Key"
private const val KEY = "2aaf6307-4662-40d1-a590-edc3524b5d1e"

