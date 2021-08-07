package com.example.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemDayBinding
import javax.inject.Inject

class DayAdapter @Inject constructor() :
    RecyclerView.Adapter<DayAdapter.WeatherViewHolder>() {

    var list = mutableListOf<BindForecastDay>()
    lateinit var binding: ItemDayBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_day, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val dataModel = list[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = list.size

    fun add(newList: BindForecastDay) {
        if(list.size > 4){
            list.clear()
        }
        list.add(newList)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(private val binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.bindDay, obj)
            binding.executePendingBindings()
        }
    }
}