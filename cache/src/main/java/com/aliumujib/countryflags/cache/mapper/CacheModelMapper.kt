package com.aliumujib.countryflags.cache.mapper

import io.reactivex.exceptions.Exceptions
import java.text.SimpleDateFormat
import java.util.*

interface CacheModelMapper<M, E> {

    fun mapFromModel(model: M): E

    fun mapToModel(model: E): M

    fun mapModelList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapFromModel(it))
        }

        return list
    }

    fun mapToModelList(models: List<E>?): List<M> {
        val list = mutableListOf<M>()
        models?.forEach {
            list.add(mapToModel(it))
        }

        return list
    }

    fun safeInt(int: Int?): Int {
        return int?: 0
    }

    fun safeList(models: List<Any>?): List<Any> {
        return models ?: emptyList()
    }

    fun safeString(string: String?): String {
        return string ?: "N/A"
    }

    fun safeBoolean(boolean: Boolean?): Boolean {
        return boolean ?: false
    }

    fun urlListToIdList(urlList: List<String>?): List<Int> {
        val idList = mutableListOf<Int>()
        urlList?.forEachIndexed { _, s ->
            idList.add(urlToId(s))
        }
        return idList
    }

    fun urlListToSingleId(urlList: List<String>?): Int {
        var id = 0
        if (urlList?.isNotEmpty() == true) {
            id = urlToId(urlList[0])
        }
        return id
    }


    fun urlToId(url: String): Int = if (url != "N/A") {
        url.replace(Regex("[^\\d]"), "").toInt()
    } else {
        -1
    }

    fun safeParse(format: SimpleDateFormat, from: String): Date {
        val date = try {
            format.parse(from)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Exceptions.propagate(ex)
            Date()
        }
        return date
    }

}