package com.akrwt.pcastudentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

class ContactActivity : AppCompatActivity() {

    private var tv2:TextView?=null
    private var tv3:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)

        val text = "Contact Us at +918979497968"
        val text2 = "Email Us at pcapauri222180@gmail.com"
        val ss = SpannableString(text)
        val ss2 = SpannableString(text2)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val number = "+918979497968"
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$number")
                startActivity(intent)
            }
        }
        val clickableSpan2 = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val email = Intent(Intent.ACTION_SENDTO)
                email.data = Uri.parse("mailto:")
                email.putExtra(Intent.EXTRA_EMAIL, arrayOf("pcapauri222180@gmail.com"))

                startActivity(Intent.createChooser(email, "Choose an email client :"))
            }
        }

        ss.setSpan(clickableSpan, 14, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss2.setSpan(clickableSpan2, 12, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv2!!.text = ss
        tv2!!.movementMethod = LinkMovementMethod.getInstance()
        tv3!!.text = ss2
        tv3!!.movementMethod = LinkMovementMethod.getInstance()
    }
}
