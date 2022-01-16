package com.wtf.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.wtf.bulletinboard.R
import com.wtf.bulletinboard.databinding.ActivityEditAdvertisementBinding
import com.wtf.bulletinboard.dialogs.DialogSpinnerHelper
import com.wtf.bulletinboard.utils.CityHelper

class EditAdvertisementActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditAdvertisementBinding
    private val dialog = DialogSpinnerHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdvertisementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){

    }

    fun onClickSelectCountry(view: View){
        val arrayCountries = CityHelper.getAllCountries(this)
        dialog.showSpinnerDialog(this, arrayCountries, binding.tvAdvertisementCountry)
        binding.tvAdvertisementCity.text = getString(R.string.select_city)
    }

    fun onClickSelectCity(view: View){
        if(binding.tvAdvertisementCountry.text.isNotEmpty() &&
            binding.tvAdvertisementCountry.text != getString(R.string.select_country)){
            val arrayCities = CityHelper.getAllCitiesOfCountry(this, binding.tvAdvertisementCountry.text.toString())
            dialog.showSpinnerDialog(this, arrayCities, binding.tvAdvertisementCity)
        } else{
            Toast.makeText(this, getString(R.string.select_country), Toast.LENGTH_SHORT).show()
        }
    }
}