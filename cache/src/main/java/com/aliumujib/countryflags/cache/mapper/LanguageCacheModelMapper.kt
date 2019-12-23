package com.aliumujib.countryflags.cache.mapper

import com.aliumujib.countryflags.cache.models.LanguageCacheModel
import com.aliumujib.countryflags.data.model.LanguageEntity
import javax.inject.Inject

class LanguageCacheModelMapper @Inject constructor() :
    CacheModelMapper<LanguageCacheModel, LanguageEntity> {

    override fun mapFromModel(model: LanguageCacheModel): LanguageEntity {
        return LanguageEntity(
            model.iso639_1,
            model.iso639_2,
            model.name,
            model.nativeName
        )
    }

    override fun mapToModel(model: LanguageEntity): LanguageCacheModel {
        return LanguageCacheModel(
            model.iso639_1,
            model.iso639_2,
            model.name,
            model.nativeName
        )
    }


}
