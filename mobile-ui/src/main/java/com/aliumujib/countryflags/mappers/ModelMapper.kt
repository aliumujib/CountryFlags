package com.aliumujib.countryflags.mappers

interface ModelMapper<P, V> {

    fun mapToView(presentation: P): V

    fun mapToPresentation(view: V): P

}