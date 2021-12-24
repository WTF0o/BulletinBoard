package com.wtf.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.wtf.bulletinboard.R
import com.wtf.bulletinboard.databinding.ActivityEditAdvertisementBinding
import com.wtf.bulletinboard.utils.CityHelper

class EditAdvertisementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAdvertisementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdvertisementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            CityHelper.getAllCountries(this)
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCountry.adapter = adapter
    }
}