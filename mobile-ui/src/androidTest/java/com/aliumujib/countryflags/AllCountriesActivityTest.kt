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
import androidx.test.rule.GrantPermissionRule
import com.aliumujib.countryflags.domain.models.Country
import com.aliumujib.countryflags.ui.AllCountriesActivity
import com.aliumujib.countryflags.ui.adapters.allcountries.AllCountriesAdapter
import com.facebook.testing.screenshot.Screenshot
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.fragment_country_details.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
class AllCountriesActivityTest {

    @Rule
    @JvmField
    var activityTestRule =
        ActivityTestRule<AllCountriesActivity>(AllCountriesActivity::class.java, false, false)

    @JvmField
    @Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @Test
    fun activityLaunchesTest() {
        stubCountriesRepositoryGetCountries(Maybe.just(UIDataFactory.makeNonRandomCountryList(3)))
        activityTestRule.launchActivity(null)
    }

    @Test
    fun fetchAllCountriesActivityTest() {
        val countryData = UIDataFactory.makeNonRandomCountryList(3)
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

        Screenshot.snapActivity(activityTestRule.activity).record()
    }

    @Test
    fun searchAllCountriesActivityTest() {
        val countryData = UIDataFactory.makeNonRandomCountryList(3)
        val searchResults = UIDataFactory.makeNonRandomCountryList(3)

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

        Screenshot.snapActivity(activityTestRule.activity).record()
    }


    @Test
    fun errorResultsShowErrorStateTest() {
        val errorMessage = "An error occurred in the back"
        stubCountriesRepositoryGetCountries(Maybe.error(Throwable(errorMessage)))

        activityTestRule.launchActivity(null)

        onView(withId(R.id.errorView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.errorView)).check(matches(withText(errorMessage)))

        Screenshot.snapActivity(activityTestRule.activity).record()
    }

    @Test
    fun emptyResultsShowEmptyStateTest() {

        stubCountriesRepositoryGetCountries(Maybe.just(emptyList()))

        activityTestRule.launchActivity(null)

        onView(withId(R.id.emptyView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        Screenshot.snapActivity(activityTestRule.activity).record()
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