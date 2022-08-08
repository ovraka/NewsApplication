package com.assignment.newsapplication.activity

import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.assignment.newsapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root)
    }
}