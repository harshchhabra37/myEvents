package com.manage1_event.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.manage1_event.event.databinding.ActivityNavigationHostBinding

//this activity acts just as a fragment holder

class NavigationHostActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNavigationHostBinding = DataBindingUtil.setContentView(this,R.layout.activity_navigation_host)

       buildDrawerLayout(binding)
    }

    fun buildDrawerLayout(binding: ActivityNavigationHostBinding){
        navController=this.findNavController(R.id.host_fragment)
        drawerLayout=binding.drawerLayout
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationView,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}