package com.aliumujib.countryflags.mappers

import com.aliumujib.countryflags.models.LanguageModel
import com.aliumujib.countryflags.presentation.models.LanguagePresentationModel
import javax.inject.Inject

class LanguageModelMapper @Inject constructor() :
    ModelMapper<LanguagePresentationModel, LanguageModel> {

    override fun mapToView(presentation: LanguagePresentationModel): LanguageModel {
        return LanguageModel(
            presentation.iso639_1,
            presentation.iso639_2,
            presentation.name,
            presentation.nativeName
        )
    }

    override fun mapToPresentation(view: LanguageModel): LanguagePresentationModel {
        return LanguagePresentationModel(
            view.iso639_1,
            view.iso639_2,
            view.name,
            view.nativeName
        )
    }
}