package com.aliumujib.countryflags.presentation.mappers

import com.aliumujib.countryflags.domain.models.Language
import com.aliumujib.countryflags.presentation.models.LanguagePresentationModel
import javax.inject.Inject

class LanguageModelPresentationMapper @Inject constructor() : PresentationMapper<Language, LanguagePresentationModel> {

    override fun mapToPresentation(domain: Language): LanguagePresentationModel {
        return LanguagePresentationModel(
            domain.iso639_1,
            domain.iso639_2,
            domain.name,
            domain.nativeName
        )
    }

    override fun mapToDomain(view: LanguagePresentationModel): Language {
        return Language(
            view.iso639_1,
            view.iso639_2,
            view.name,
            view.nativeName
        )
    }


}