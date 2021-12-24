package com.wtf.bulletinboard.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

object CityHelper {
    fun getAllCountries(context: Context): ArrayList<String> {
        var countriesArray = ArrayList<String>()
        try {

            val inputStream: InputStream = context.assets.open("countriesToCities.json")
            val size: Int = inputStream.available()
            val bytesArray = ByteArray(size)
            inputStream.read(bytesArray)

            val jsonFile = String(bytesArray)
            val jsonObject = JSONObject(jsonFile)
            val countriesNames = jsonObject.names()

            for (index in 0 until countriesNames.length()) {
                countriesArray.add(countriesNames.getString(index))
            }


        } catch (e: IOException) {

        }

        return countriesArray
    }
}