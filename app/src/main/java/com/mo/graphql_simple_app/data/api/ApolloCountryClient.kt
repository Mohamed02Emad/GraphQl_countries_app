package com.mo.graphql_simple_app.data.api

import com.apollographql.apollo3.ApolloClient
import com.mo.CountriesQuery
import com.mo.CountryQuery
import com.mo.graphql_simple_app.data.mapper.toDetailedCountry
import com.mo.graphql_simple_app.data.mapper.toSimpleCountry
import com.mo.graphql_simple_app.domain.api.CountryClientInterface
import com.mo.graphql_simple_app.domain.models.DetailedCountry
import com.mo.graphql_simple_app.domain.models.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
) : CountryClientInterface {
    override suspend fun getAllCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { country ->
                country.toSimpleCountry()
            }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }


}