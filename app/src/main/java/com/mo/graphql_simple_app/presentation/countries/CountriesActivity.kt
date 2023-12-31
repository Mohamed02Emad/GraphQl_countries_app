package com.mo.graphql_simple_app.presentation.countries

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import com.mo.graphql_simple_app.databinding.ActivityCountriesBinding
import com.mo.graphql_simple_app.domain.models.DetailedCountry
import com.mo.graphql_simple_app.domain.models.SimpleCountry
import com.mo.graphql_simple_app.presentation.adapters.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by viewModels()
    private val binding: ActivityCountriesBinding by lazy {
        ActivityCountriesBinding.inflate(layoutInflater)
    }
    private lateinit var countriesAdapter : CountriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCollectors()
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            viewModel.countriesState.collectLatest { state ->
                setLoadingVisibility(state.isLoading)
                if (state.countries.isNullOrEmpty().not()){
                    setUpRecyclerView(state.countries!!)
                    viewModel.clearCountries()
                }
                if (state.country != null) {
                    openCountryDialog(state.country)
                    viewModel.clearCountry()
                }
            }
        }
    }

    private fun openCountryDialog(country: DetailedCountry) {
        binding.apply {
            dialog.apply {
                tvCapital.text = country.capital
                tvContinent.text = country.continent
                tvCurrency.text = country.currency
                tvName.text = country.name
                tvEmoji.text = country.emoji
            }
            dialogFrame.isGone = false
            lifecycleScope.launch {
                delay(2500)
                dialogFrame.isGone = true
            }
        }
    }

    private fun setUpRecyclerView(countries: List<SimpleCountry>) {
        countriesAdapter = CountriesAdapter(countries) { country ->
            onCountryClicked(country)
        }
        binding.countriesRv.adapter = countriesAdapter
    }

    private fun onCountryClicked(country: SimpleCountry) {
        viewModel.getCountry(country.code)
    }

    private fun setLoadingVisibility(isLoading: Boolean) {
        if (isLoading){
            showLoading()
        }else{
            hideLoading()
        }
    }

    private fun showLoading(){
        binding.prLoading.isGone = false
    }

    private fun hideLoading(){
        binding.prLoading.isGone = true
    }

    private fun showToast(message : String){
        Toast.makeText(this,message , Toast.LENGTH_SHORT).show()
    }
}