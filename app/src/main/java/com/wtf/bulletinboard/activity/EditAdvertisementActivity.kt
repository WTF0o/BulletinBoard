package com.wtf.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.wtf.bulletinboard.R
import com.wtf.bulletinboard.databinding.ActivityEditAdvertisementBinding
import com.wtf.bulletinboard.dialogs.DialogSpinnerHelper
import com.wtf.bulletinboard.utils.CityHelper

class EditAdvertisementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAdvertisementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdvertisementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayCountries = CityHelper.getAllCountries(this)
        val dialog = DialogSpinnerHelper()
        dialog.showSpinnerDialog(this, arrayCountries)

    }
}