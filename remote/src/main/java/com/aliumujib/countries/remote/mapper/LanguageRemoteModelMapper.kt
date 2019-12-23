package com.aliumujib.countries.remote.mapper

import com.aliumujib.countries.remote.countries.LanguageRemoteModel
import com.aliumujib.countryflags.data.model.LanguageEntity
import javax.inject.Inject

class LanguageRemoteModelMapper @Inject constructor() :
    RemoteModelMapper<LanguageRemoteModel, LanguageEntity> {


    override fun mapFromModel(model: LanguageRemoteModel): LanguageEntity {
        return LanguageEntity(
            safeString(model.iso639_1),
            safeString(model.iso639_2),
            safeString(model.name),
            safeString(model.nativeName)
        )
    }


}