package com.aliumujib.countryflags.ui.countrydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.models.CountryModel

class CountryDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    companion object {
        fun newInstance(countryModel: CountryModel): CountryDetailsFragment {
            val fragment = CountryDetailsFragment()
            fragment.apply {

            }
            return fragment
        }
    }
}
