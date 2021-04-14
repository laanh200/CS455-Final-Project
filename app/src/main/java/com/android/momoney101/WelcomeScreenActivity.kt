package com.android.momoney101

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class WelcomeScreenActivity : AppCompatActivity() {
    private lateinit var welcomeLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        //Link the image id and image view
        welcomeLogo = findViewById(R.id.welcome_logo)

        welcomeLogo.alpha = 0f

        //Animate the welcome screen and then set delay
        welcomeLogo.animate().setDuration(1500).alpha(1f).withEndAction(){
            //After the delay go to the main activity screen
            val intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)
            //Set the transition
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}