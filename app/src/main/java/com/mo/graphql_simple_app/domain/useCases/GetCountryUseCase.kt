package com.mo.graphql_simple_app.domain.useCases

import com.mo.graphql_simple_app.domain.api.CountryClientInterface
import com.mo.graphql_simple_app.domain.models.DetailedCountry
import javax.inject.Inject

class GetCountryUseCase@Inject constructor(
    private val countryClientInterface: CountryClientInterface
)  {
    suspend fun execute(code : String) : DetailedCountry?{
        return countryClientInterface.getCountry(code)
    }
}