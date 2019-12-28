package com.aliumujib.countryflags

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.ui.AllCountriesActivity
import com.aliumujib.countryflags.ui.adapters.allcountries.AllCountriesAdapter
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
    var activityTestRule =
        ActivityTestRule<AllCountriesActivity>(AllCountriesActivity::class.java, false, false)


    @Test
    fun activityLaunchesTest() {
        stubCountriesRepositoryGetCountries(Maybe.just(UIDataFactory.makeCountryList(3)))
        activityTestRule.launchActivity(null)
    }

    @Test
    fun fetchAllCountriesActivityTest() {
        val countryData = UIDataFactory.makeCountryList(3)

        stubCountriesRepositoryGetCountries(Maybe.just(countryData))

        activityTestRule.launchActivity(null)

        countryData.forEachIndexed { index, country ->
            onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.scrollToPosition<AllCountriesAdapter.ItemViewHolder>(
                    index
                )
            )

            onView(withId(R.id.recycler_view)).check(
                matches(hasDescendant(withText(country.name)))
            )
        }

    }

    @Test
    fun searchAllCountriesActivityTest() {
        val countryData = UIDataFactory.makeCountryList(3)
        val searchResults = UIDataFactory.makeCountryList(3)

        stubCountriesRepositoryGetCountries(Maybe.just(countryData))
        stubCountriesRepositorySearchCountries(Maybe.just(searchResults))

        activityTestRule.launchActivity(null)

        onView(withId(R.id.app_search)).perform(click())

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText(searchResults[0].name))

        searchResults.forEachIndexed { index, country ->
            onView(withId(R.id.recycler_view)).perform(
                RecyclerViewActions.scrollToPosition<AllCountriesAdapter.ItemViewHolder>(
                    index
                )
            )

            onView(withId(R.id.recycler_view)).check(
                matches(hasDescendant(withText(country.name)))
            )
        }

    }

    private fun stubCountriesRepositorySearchCountries(maybe: Maybe<List<Country>>) {
        whenever(TestApplicationClass.countriesRepository().searchCountries(any()))
            .thenReturn(maybe)
    }

    private fun stubCountriesRepositoryGetCountries(maybe: Maybe<List<Country>>) {
        whenever(TestApplicationClass.countriesRepository().fetchCountries(any()))
            .thenReturn(maybe)
    }
}