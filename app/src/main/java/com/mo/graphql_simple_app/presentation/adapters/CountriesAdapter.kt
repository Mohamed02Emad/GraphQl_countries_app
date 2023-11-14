package com.mo.graphql_simple_app.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mo.graphql_simple_app.databinding.LayoutCountryBinding
import com.mo.graphql_simple_app.domain.models.SimpleCountry
import com.mo.graphql_simple_app.utils.loadImageWithGlide


class CountriesAdapter(
    var countries: List<SimpleCountry>,
    private val onCardClick: (SimpleCountry) -> Unit,
) : RecyclerView.Adapter<CountriesAdapter.CountriesAdapterViewHolder>() {

    inner class CountriesAdapterViewHolder(val binding: LayoutCountryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapterViewHolder {
        return CountriesAdapterViewHolder(
            LayoutCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = countries.size


    override fun onBindViewHolder(holder: CountriesAdapterViewHolder, position: Int) {
        val item = countries[position]
        setViews(holder, item)
        setOnClicks(holder, item)
    }

    private fun setViews(holder: CountriesAdapterViewHolder, item: SimpleCountry) {
        holder.binding.apply {
//            ivCountryEmoji.loadImageWithGlide(item.emoji)
            ivCountryEmoji.text = item.emoji
            tvCountryName.text = item.name
            tvCountryCapital.text = item.capital
        }
    }

    private fun setOnClicks(holder: CountriesAdapterViewHolder, item: SimpleCountry) {
        holder.binding.apply {
            root.setOnClickListener {
                onCardClick(item)
            }
        }
    }

}