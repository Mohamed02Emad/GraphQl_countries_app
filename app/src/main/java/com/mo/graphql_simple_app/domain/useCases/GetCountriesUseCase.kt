package com.mo.graphql_simple_app.domain.useCases

import com.mo.graphql_simple_app.domain.api.CountryClientInterface
import com.mo.graphql_simple_app.domain.models.SimpleCountry
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryClientInterface: CountryClientInterface
) {
    suspend fun execute():List<SimpleCountry>{
        return countryClientInterface.getAllCountries().sortedBy { it.name }
    }
}