package com.akrwt.pcastudentapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class UserFragment : Fragment() {

    private val studentsDetailArray = arrayOf(
        "Name",
        "Class",
        "Date of birth",
        "Contact",
        "Father's Name",
        "Mother's Name"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_user, container, false)
        val sIDTV = v.findViewById<TextView>(R.id.studentIDTV)
        val adView = v.findViewById<AdView>(R.id.adView)
        MobileAds.initialize(context, "ca-app-pub-3760298764731143~4190679536")
        val adReq = AdRequest.Builder().build()
        adView.loadAd(adReq)

        val adapter = ArrayAdapter(context!!, R.layout.student_layout, studentsDetailArray)

        val listView = v.findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        val sharedPref = activity!!.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val id = "Student ID: " + sharedPref.getString("id", "-")!!
        sIDTV.text = id

        studentsDetailArray[0] = "Name: " + sharedPref.getString("name", "-")!!
        studentsDetailArray[1] = "Class: " + sharedPref.getString("mClass", "-")!!
        studentsDetailArray[2] = "DOB: " + sharedPref.getString("doB", "-")!!
        studentsDetailArray[3] = "Contact: " + sharedPref.getString("contact", "-")!!
        studentsDetailArray[4] = "Father's Name: " + sharedPref.getString("fName", "-")!!
        studentsDetailArray[5] = "Mother's Name: " + sharedPref.getString("mName", "-")!!

        adapter.notifyDataSetChanged()
        return v
    }
}
