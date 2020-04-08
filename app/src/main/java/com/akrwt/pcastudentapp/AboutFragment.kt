package com.akrwt.pcastudentapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class AboutFragment : Fragment() {

    private var sign_out: CardView?=null
    private var contact_us:CardView?=null
    private var feedback:CardView?=null
    private var about:CardView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_about, container, false)

        sign_out = v.findViewById(R.id.sign_out)
        contact_us = v.findViewById(R.id.contact_us)
        feedback = v.findViewById(R.id.feedback)
        about = v.findViewById(R.id.about)

        sign_out!!.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
            val preferences = activity!!.getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            activity!!.finishAffinity()
        }

        contact_us!!.setOnClickListener{
            startActivity(Intent(context, ContactActivity::class.java))
        }
        feedback!!.setOnClickListener{
            startActivity(Intent(context,FeedbackActivity::class.java))
        }
        about!!.setOnClickListener{
            startActivity(Intent(context, AboutActivity::class.java))
        }

        return v
    }

}
