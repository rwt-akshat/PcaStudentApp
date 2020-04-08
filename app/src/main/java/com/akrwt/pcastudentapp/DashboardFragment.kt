package com.akrwt.pcastudentapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.firebase.database.*

class DashboardFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: DashboardAdapter
    private val arr: MutableList<ModelClass>? = ArrayList()

    private var prBar: ProgressBar? = null
    private var nothingText: TextView? = null
    private var nothingImage: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_dashboard, container, false)

        prBar = v.findViewById(R.id.prBar)
        nothingText = v.findViewById(R.id.nothingText)
        nothingImage = v.findViewById(R.id.nothingImage)

        mRecyclerView = v.findViewById(R.id.announcementList)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.setHasFixedSize(true)

        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("Announcements")

        mDatabaseRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                arr!!.clear()
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.children.forEach { dS ->
                        val u = dS.getValue(ModelClass::class.java)
                        arr.add(u!!)
                    }
                }

                arr.reverse()
                if (context != null && prBar != null) {
                    mAdapter = DashboardAdapter(context!!, arr)
                    mRecyclerView.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()

                    if (arr.isNotEmpty()) {
                        prBar!!.visibility = View.INVISIBLE
                        nothingText!!.visibility = View.INVISIBLE
                        nothingImage!!.visibility = View.INVISIBLE
                    } else {
                        prBar!!.visibility = View.INVISIBLE
                        nothingText!!.visibility = View.VISIBLE
                        nothingImage!!.visibility = View.VISIBLE
                    }
                }
            }
        })
        return v
    }
}
