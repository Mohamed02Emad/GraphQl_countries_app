package com.mo.graphql_simple_app.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mo.graphql_simple_app.domain.useCases.GetCountriesUseCase
import com.mo.graphql_simple_app.domain.useCases.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _countriesState = MutableStateFlow(CountriesScreenState())
    val countriesState = _countriesState.asStateFlow()


    init {
        viewModelScope.launch {
            _countriesState.update {
                it.copy(
                    isLoading = true
                )
            }
            val countries = getCountriesUseCase.execute()
            _countriesState.update {
                it.copy(
                    countries = countries,
                    isLoading = false,
                )
            }
        }
    }

    fun getCountry(code: String) {
        viewModelScope.launch {
            _countriesState.update {
                it.copy(
                    isLoading = true
                )
            }
            val country = getCountryUseCase.execute(code)
            country?.let { country->
                _countriesState.update{
                    it.copy(
                        country = country,
                        isLoading = false,
                    )
                }
            }

        }
    }

    fun clearCountry() {
        _countriesState.update {
            it.copy(
                country = null
            )
        }
    }

    fun clearCountries() {
        _countriesState.update {
            it.copy(
                countries = null,
            )
        }
    }

}