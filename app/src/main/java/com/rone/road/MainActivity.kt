package com.rone.road

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.rone.road.R.id.drawerLayout
import com.rone.road.R.id.navView
import com.rone.road.R.layout.activity_main
import com.rone.road.fragmentExplore.exploreFragment
import com.rone.road.fragmentProfile.profileFragment
import com.rone.road.fragmentSearch.fragmentSearch

class MainActivity : AppCompatActivity() {
    //lateinit makes the promise that we will initialize the toggle
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        //Let's create the toggle button
        toggle = ActionBarDrawerToggle(this, findViewById(drawerLayout) , R.string.open, R.string.close)
        //remember to specify the kind of object that we're looking for 'drawerlayout'
        findViewById<DrawerLayout>(drawerLayout).addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val firstFragment = exploreFragment()
        val secondFragment = fragmentSearch()
        val thirdFragment = profileFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, firstFragment)
            commit()
        }

        findViewById<NavigationView>(navView).setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.myItem1 ->    supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, firstFragment)
                    commit()
                }
                R.id.myItem2 ->    supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, secondFragment)
                    commit()
                }
                R.id.myItem3 ->    supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, thirdFragment)
                    commit()
                }
            }
            true
        }





    }
    //this function will make the items select
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }




}