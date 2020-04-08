package com.akrwt.pcastudentapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =

            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.dashboard -> {
                        replaceFragment(DashboardFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.userDetails -> {
                        replaceFragment(UserFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.about -> {
                        replaceFragment(AboutFragment())
                        return@OnNavigationItemSelectedListener true
                    }

                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("Students")

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(DashboardFragment())

    }

    override fun onBackPressed() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val selectedItemId = bottomNavigationView.selectedItemId
        if (R.id.dashboard != selectedItemId) {
            setDashboardItem(this@MainActivity)
        } else {
            super.onBackPressed()
        }
    }


    private fun setDashboardItem(activity: Activity) {
        val bottomNavigationView =
                activity.findViewById<View>(R.id.bottomNavigation) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.dashboard
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}



