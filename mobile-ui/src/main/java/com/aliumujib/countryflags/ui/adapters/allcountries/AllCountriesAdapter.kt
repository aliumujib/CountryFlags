package com.aliumujib.countryflags.ui.adapters.allcountries

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.models.HeaderModel
import com.aliumujib.countryflags.ui.utils.imageloader.ImageLoader
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*
import javax.inject.Inject
import kotlin.Comparator


class AllCountriesAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    private val itemClickPublisher: PublishSubject<CountryModel>
) : Adapter<ViewHolder>() {

    private var modelList: MutableList<AllCountriesAdapterBindable> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == SECTION_VIEW) {
            SectionHeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_header_title,
                    parent,
                    false
                )
            )
        } else ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.country_item,
                parent,
                false
            )
            , imageLoader, itemClickPublisher
        )
    }

    fun itemClicks(): Observable<CountryModel> {
        return itemClickPublisher.take(1)
    }

    /**
     * I could have done this using a hashmap to store characters and header positions and not need to created a more complex multi-typed list
     * but it would have made it almost impossible to use DiffUtils and I don't want to make that tradeoff
     * */

    private fun sectionCountries(countryList: List<CountryModel>): MutableList<AllCountriesAdapterBindable> {
        val countries = mutableListOf<AllCountriesAdapterBindable>()
        Collections.sort(countryList, Comparator<CountryModel> { user1, user2 ->
            user1.name[0].toUpperCase()
                .compareTo(user2.name[0])
        })
        var lastHeader: String? = ""
        val size: Int = countryList.size
        for (i in 0 until size) {
            val countryModel: CountryModel = countryList[i]
            val header: String = countryModel.name[0].toString()
            if (!TextUtils.equals(lastHeader, header)) {
                lastHeader = header
                countries.add(HeaderModel(header))
            }
            countries.add(countryModel)
        }
        return countries
    }


    override fun getItemViewType(position: Int): Int {
        return if (modelList[position] is HeaderModel) {
            SECTION_VIEW
        } else {
            CONTENT_VIEW
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == SECTION_VIEW) {
            val sectionHeaderViewHolder =
                holder as SectionHeaderViewHolder
            val headerModel: HeaderModel = modelList[position] as HeaderModel
            sectionHeaderViewHolder.bind(headerModel)
        } else {
            val itemViewHolder =
                holder as ItemViewHolder
            val countryModel: CountryModel = modelList[position] as CountryModel
            itemViewHolder.bind(countryModel)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun updateData(data: List<CountryModel>) {
        modelList = sectionCountries(data)
        notifyDataSetChanged()
    }

    class ItemViewHolder(
        itemView: View,
        private val imageLoader: ImageLoader,
        private val itemClickPublisher: PublishSubject<CountryModel>
    ) : ViewHolder(itemView) {
        private val nameTextView: TextView =
            itemView.findViewById<View>(R.id.country_name) as TextView
        private val countryFlag: ImageView =
            itemView.findViewById<View>(R.id.country_logo) as ImageView

        fun bind(countryModel: CountryModel) {
            (itemView as ViewGroup).forEach { child ->
                child.setOnClickListener {
                    itemClickPublisher.onNext(countryModel)
                }
            }
            nameTextView.text = countryModel.name
            imageLoader.loadImage(countryModel.flag, countryFlag)
        }
    }

    class SectionHeaderViewHolder(itemView: View) : ViewHolder(itemView) {
        private val headerTitleTextview: TextView =
            itemView.findViewById<View>(R.id.header_title) as TextView

        fun bind(headerModel: HeaderModel) {
            headerTitleTextview.text = headerModel.headerCharacter
        }
    }

    companion object {
        const val SECTION_VIEW = 0
        const val CONTENT_VIEW = 1
    }

}