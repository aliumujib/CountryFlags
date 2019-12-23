package com.aliumujib.countryflags.data.mapper

import com.aliumujib.countryflags.data.mapper.base.EntityMapper
import com.aliumujib.countryflags.data.model.LanguageEntity
import com.aliumujib.countryflags.domain.models.Language
import javax.inject.Inject

class LanguageEntityMapper @Inject constructor() : EntityMapper<LanguageEntity, Language>() {

    override fun mapFromEntity(entity: LanguageEntity): Language {
        return Language(
            entity.iso639_1,
            entity.iso639_2,
            entity.name,
            entity.nativeName
        )
    }

    override fun mapToEntity(domain: Language): LanguageEntity {
        return LanguageEntity(
            domain.iso639_1,
            domain.iso639_2,
            domain.name,
            domain.nativeName
        )
    }


}
