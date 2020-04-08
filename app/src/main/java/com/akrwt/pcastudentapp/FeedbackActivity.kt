package com.akrwt.pcastudentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val sendBtn = findViewById<Button>(R.id.sendBtn)
        val adView = findViewById<AdView>(R.id.adView)
        val feedbackET = findViewById<EditText>(R.id.etFeedback)
        MobileAds.initialize(this, "ca-app-pub-3760298764731143~4190679536")
        val adReq = AdRequest.Builder().build()
        adView.loadAd(adReq)

        sendBtn.setOnClickListener {
            val feedback = feedbackET.text.toString()

            if (feedback.isEmpty()) {
                Toast.makeText(applicationContext, "Feedback field is empty", Toast.LENGTH_SHORT).show()
            } else {
                if (feedback.length < 10) {
                    Toast.makeText(
                        applicationContext,
                        "Feedback should be of length greater than 10 .. ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val email = Intent(Intent.ACTION_SENDTO)
                    email.data = Uri.parse("mailto:")
                    email.putExtra(Intent.EXTRA_EMAIL, arrayOf("akshatrwt00@gmail.com"))
                    email.putExtra(Intent.EXTRA_TEXT, feedback)
                    email.putExtra(Intent.EXTRA_SUBJECT, "FEEDBACK")
                    startActivity(Intent.createChooser(email, "Choose an email client :"))
                }
            }
        }
    }
}
