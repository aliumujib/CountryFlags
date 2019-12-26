package com.aliumujib.countryflags.ui.adapters.allcountries

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aliumujib.countryflags.R
import com.aliumujib.countryflags.models.CountryModel
import com.aliumujib.countryflags.models.HeaderModel
import com.aliumujib.countryflags.ui.utils.imageloader.ImageLoader
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.childrenRecursiveSequence
import java.util.*
import javax.inject.Inject
import kotlin.Comparator


class AllCountriesAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    val itemClickPublisher: PublishSubject<CountryModel>
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
        )
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
            val sectionItem: HeaderModel = modelList[position] as HeaderModel
            sectionHeaderViewHolder.headerTitleTextview.text = sectionItem.headerCharacter
            return
        } else {
            val itemViewHolder =
                holder as ItemViewHolder
            val countryModel: CountryModel = modelList[position] as CountryModel
            itemViewHolder.nameTextView.text = countryModel.name
            imageLoader.loadImage(countryModel.flag, itemViewHolder.countryFlag)
            itemViewHolder.itemView.childrenRecursiveSequence().forEach { child ->
                child.setOnClickListener {
                    itemClickPublisher.onNext(countryModel)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun updateData(data: List<CountryModel>) {
        modelList = sectionCountries(data)
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById<View>(R.id.country_name) as TextView
        val countryFlag: ImageView = itemView.findViewById<View>(R.id.country_logo) as ImageView

    }

    inner class SectionHeaderViewHolder(itemView: View) : ViewHolder(itemView) {
        internal val headerTitleTextview: TextView =
            itemView.findViewById<View>(R.id.header_title) as TextView

    }

    companion object {
        const val SECTION_VIEW = 0
        const val CONTENT_VIEW = 1
    }

}