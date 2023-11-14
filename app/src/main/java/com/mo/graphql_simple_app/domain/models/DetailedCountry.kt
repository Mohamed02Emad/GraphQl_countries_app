package com.mo.graphql_simple_app.domain.models

import com.mo.CountryQuery

data class DetailedCountry (
     val name: String,
     val emoji: String,
     val code: String,
     val capital: String?,
     val currency: String?,
     val languages: List<CountryQuery.Language>,
     val continent: String,
)