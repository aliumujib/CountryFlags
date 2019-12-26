package com.aliumujib.countryflags.cache.room

import androidx.room.*
import com.aliumujib.countryflags.cache.models.CountryCacheModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: CountryCacheModel)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(countries: List<CountryCacheModel>)


    @Query("DELETE FROM COUNTRIES")
    fun deleteAllCountries()

    @Query("SELECT * FROM COUNTRIES")
    fun getAllCountries(): Maybe<List<CountryCacheModel>>

}