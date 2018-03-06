package com.dudu.project.lovelycatgank

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by admin on 2018/3/4.
 */
class LaunchActivtity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        Fresco.initialize(getApplicationContext())
        val intent = Intent()
        intent.setClass(this,MainActivity::class.java)
        startActivity(intent)
    }
}