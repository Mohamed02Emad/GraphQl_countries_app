package com.mo.graphql_simple_app.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.mo.graphql_simple_app.data.api.ApolloCountryClient
import com.mo.graphql_simple_app.domain.api.CountryClientInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideContext(
        @ApplicationContext appContext: Context,
    ): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloCountryClient(apolloClient: ApolloClient): CountryClientInterface {
        return ApolloCountryClient(apolloClient)
    }

}