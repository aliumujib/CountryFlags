package com.aliumujib.countryflags.presentation.mappers

interface PresentationMapper<D, P> {

    fun mapToPresentation(domain: D): P

    fun mapToDomain(view: P): D

}