package com.assignment.notely.ui

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import com.assignment.notely.ui.viewnote.ViewNoteActivity


/**
 * Created by vivek on 22/01/18.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ViewNoteActivity::class.java)
        startActivity(intent)
        finish()
    }
}