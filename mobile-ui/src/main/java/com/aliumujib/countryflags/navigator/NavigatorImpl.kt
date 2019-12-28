package com.aliumujib.countryflags.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.ui.allcountries.AllCountriesFragment
import com.aliumujib.countryflags.ui.countrydetail.CountryDetailsFragment
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
        val fragment: Fragment =
            findFragmentByTag(AllCountriesFragment.TAG, AllCountriesFragment.newInstance())
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.replace(
            R.id.mainHostFragment,
            fragment,
            AllCountriesFragment.TAG
        ).commit()
    }

    private fun findFragmentByTag(tag: String, newInstance: Fragment): Fragment {
        return if (fragmentManager.findFragmentByTag(tag) == null) {
            newInstance
        } else {
            fragmentManager.findFragmentByTag(tag)!!
        }
    }

    override fun goToDetailScreen(countryModel: CountryModel) {
        val fragment = findFragmentByTag(
            CountryDetailsFragment.TAG,
            CountryDetailsFragment.newInstance(countryModel)
        )
        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.replace(
            R.id.mainHostFragment,
            fragment,
            CountryDetailsFragment.TAG
        ).addToBackStack(CountryDetailsFragment::class.java.simpleName)
            .commit()
    }


    override fun setupActionBar() {
        if (fragmentManager.backStackEntryCount <= 0) {
            activity.supportActionBar?.apply {
                this.setDisplayHomeAsUpEnabled(false)
                this.setHomeAsUpIndicator(null)
            }
        } else {
            activity.supportActionBar?.apply {
                this.setDisplayHomeAsUpEnabled(true)
                this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
            }
        }
    }

    override fun goBack() {
        fragmentManager.popBackStack()
    }

}
