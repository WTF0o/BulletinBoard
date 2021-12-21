package com.wtf.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wtf.bulletinboard.R
import com.wtf.bulletinboard.databinding.ActivityEditAdvertisementBinding

class EditAdvertisementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAdvertisementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdvertisementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}