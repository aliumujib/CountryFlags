package com.aliumujib.countryflags

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.ui.AllCountriesActivity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AllCountriesActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule<AllCountriesActivity>(AllCountriesActivity::class.java, false, false)


    @Test
    fun activityLaunchesTest(){
        stubCountriesRepositoryGetCountries(Maybe.just(UIDataFactory.makeCountryList(3)))
        activityTestRule.launchActivity(null)
    }

    @Test
    fun searchAllCountriesActivityTest() {
        stubCountriesRepositoryGetCountries(Maybe.just(UIDataFactory.makeCountryList(3)))
        stubCountriesRepositorySearchCountries(Maybe.just(UIDataFactory.makeCountryList(3)))
        Thread.sleep(5000)
        activityTestRule.launchActivity(null)

        onView(withId(R.id.app_search)).perform(click())

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("nigeria"))

    }

    private fun stubCountriesRepositorySearchCountries(maybe: Maybe<List<Country>>) {
        whenever(TestApplicationClass.appComponent().countriesRepository().searchCountries(any()))
            .thenReturn(maybe)
    }

    private fun stubCountriesRepositoryGetCountries(maybe: Maybe<List<Country>>) {
        whenever(TestApplicationClass.appComponent().countriesRepository().fetchCountries(any()))
            .thenReturn(maybe)
    }
}