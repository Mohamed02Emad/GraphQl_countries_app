package com.mo.graphql_simple_app.presentation.countries

import com.mo.graphql_simple_app.domain.models.DetailedCountry
import com.mo.graphql_simple_app.domain.models.SimpleCountry

data class CountriesScreenState(
    val country : DetailedCountry? =null,
    val countries : List<SimpleCountry>? =null,
    val isLoading : Boolean =false,
    val isSuccess : Boolean =false,
)
