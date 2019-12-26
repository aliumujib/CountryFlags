package com.aliumujib.countryflags.navigator

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.NavigationUI
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.ui.allcountries.AllCountriesFragment
import com.aliumujib.countryflags.ui.countrydetail.CountryDetailsFragment
import com.aliumujib.countryflags.ui.utils.ext.delay
import java.util.*
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager
) : Navigator {

    init {
        fragmentManager.addOnBackStackChangedListener {
            setupActionBar()
        }
    }

    override fun showCountryList() {
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.replace(R.id.mainHostFragment, AllCountriesFragment.newInstance())
            .commit()
    }

    override fun goToDetailScreen(countryModel: CountryModel) {
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.add(
            R.id.mainHostFragment,
            CountryDetailsFragment.newInstance(countryModel)
        ).addToBackStack("CountryDetailsFragment")
            .commit()
    }


    override fun setupActionBar() {
        if (fragmentManager.backStackEntryCount <= 0) {
            activity.supportActionBar?.apply {
                this.setDisplayHomeAsUpEnabled(false);
                this.setHomeAsUpIndicator(null)
            }
        } else {
            activity.supportActionBar?.apply {
                this.setDisplayHomeAsUpEnabled(true);
                this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
            }
        }
    }

    override fun goBack() {
        fragmentManager.popBackStack()
        setupActionBar()
    }

}
