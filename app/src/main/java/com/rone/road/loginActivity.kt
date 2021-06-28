package com.rone.road

import android.content.Intent
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        startBackgroundVideo()

    }


    fun newLogin(view: View){
        val startMain = Intent(this, MainActivity::class.java)
        startActivity(startMain)
        finish()
    }
    fun register(view: View){
        val startRegister = Intent(this, registerActivity::class.java)
        startActivity(startRegister)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        startBackgroundVideo()
    }

    private fun startBackgroundVideo(){
        val videoview = findViewById<View>(R.id.videoView) as VideoView
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.videobackground)
        videoview.setVideoURI(uri)
        videoview.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })
        videoview.start()
    }

}