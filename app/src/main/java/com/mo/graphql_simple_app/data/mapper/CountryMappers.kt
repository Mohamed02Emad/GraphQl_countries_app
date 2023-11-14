package com.mo.graphql_simple_app.data.mapper

import com.mo.CountriesQuery
import com.mo.CountryQuery
import com.mo.graphql_simple_app.domain.models.DetailedCountry
import com.mo.graphql_simple_app.domain.models.SimpleCountry

fun CountryQuery.Country.toDetailedCountry() : DetailedCountry {
   return DetailedCountry(
       name = this.name,
       emoji = this.emoji,
       code = this.code,
       capital = this.capital,
       currency = this.currency,
       languages = this.languages,
       continent = this.continent
   )
}


fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        name = this.name,
        emoji = this.emoji,
        code = this.code,
        capital = this.capital
    )
}