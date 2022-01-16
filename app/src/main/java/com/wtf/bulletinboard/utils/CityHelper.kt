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

    fun getAllCitiesOfCountry(context: Context, country: String): ArrayList<String> {
        var citiesArray = ArrayList<String>()
        try {

            val inputStream: InputStream = context.assets.open("countriesToCities.json")
            val size: Int = inputStream.available()
            val bytesArray = ByteArray(size)
            inputStream.read(bytesArray)

            val jsonFile = String(bytesArray)
            val jsonObject = JSONObject(jsonFile)
            val citiesName = jsonObject.getJSONArray(country)

            for (index in 0 until citiesName.length()) {
                citiesArray.add(citiesName.getString(index))
            }


        } catch (e: IOException) {

        }

        return citiesArray
    }

    fun filterListData(list: ArrayList<String>, textFilter: String?): ArrayList<String>{
        val tempList = arrayListOf<String>()

        if(textFilter == null){
            tempList.add("no result")
            return tempList
        }

        for(selection: String in list){
            if(selection.lowercase().startsWith(textFilter.lowercase())){
                tempList.add(selection)
            }
        }
        if(tempList.size == 0 )
            tempList.add("no result")
        else
            tempList

        return tempList
    }
}