package com.aliumujib.countryflags.ui.countrydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.ui.utils.imageloader.ImageLoader
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country_details.*
import java.text.DecimalFormat
import javax.inject.Inject

class CountryDetailsFragment : DaggerFragment() {

    private val country: CountryModel by lazy {
        arguments!!.getParcelable<CountryModel>(COUNTRY)
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageLoader.loadImage(country.flag, image)
        country_name.text = country.name
        country_details.text = getString(
            R.string.country_desc_format,
            country.name,
            country.subregion,
            DecimalFormat("#,###,###").format(country.population),
            country.capital
        )
        calling_codes.setText(
            getString(
                R.string.calling_code_format,
                country.callingCodes.firstOrNull()
            )
        )
    }

    companion object {
        private const val COUNTRY = "COUNTRY"
        const val TAG = "CountryDetailsFragmentTag"

        fun newInstance(countryModel: CountryModel): CountryDetailsFragment {
            val fragment = CountryDetailsFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(COUNTRY, countryModel)
            }
            return fragment
        }
    }
}
