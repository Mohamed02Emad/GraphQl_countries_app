package com.mo.graphql_simple_app.domain.api

import com.mo.graphql_simple_app.domain.models.SimpleCountry
import com.mo.graphql_simple_app.domain.models.DetailedCountry

interface CountryClientInterface {
suspend fun getAllCountries(): List<SimpleCountry>
suspend fun getCountry(code : String): DetailedCountry?

}