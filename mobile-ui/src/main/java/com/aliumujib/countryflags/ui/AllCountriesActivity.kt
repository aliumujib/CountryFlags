package com.aliumujib.countryflags.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.aliumujib.countryflags.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

class AllCountriesActivity : AppCompatActivity() {

    private val navController : NavController by lazy {
        this.findNavController(R.id.mainHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(toolbar, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}
