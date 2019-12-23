package com.aliumujib.countryflags.cache.room

import androidx.room.*
import com.aliumujib.countryflags.cache.models.CountryCacheModel
import io.reactivex.Flowable

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: CountryCacheModel)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: List<CountryCacheModel>)


    @Query("DELETE FROM COUNTRIES")
    fun deleteAllCountries()

    @Query("SELECT * FROM COUNTRIES")
    fun getAllCountries(): Flowable<List<CountryCacheModel>>

}