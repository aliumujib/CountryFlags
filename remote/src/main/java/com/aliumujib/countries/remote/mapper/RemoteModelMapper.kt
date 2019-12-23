package com.aliumujib.countries.remote.mapper

import io.reactivex.exceptions.Exceptions
import java.text.SimpleDateFormat
import java.util.*

interface RemoteModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

    fun mapModelList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapFromModel(it))
        }

        return list
    }

    fun safeList(models: List<Any>?): List<Any> {
        return models ?: emptyList()
    }

    fun safeString(string: String?): String {
        return string ?: "N_A"
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

    fun safeDouble(value: Double?): Double {
        return value ?: 0.0
    }

    fun safeInt(value: Int?): Int {
        return value ?: 0
    }

}