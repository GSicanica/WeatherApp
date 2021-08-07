package com.example.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemHourBinding
import javax.inject.Inject

class HourAdapter @Inject constructor() :
    RecyclerView.Adapter<HourAdapter.HourViewHolder>() {

    var list = mutableListOf<BindForecastHour>()
    lateinit var binding: ItemHourBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_hour,
            parent,
            false
        )
        return HourViewHolder(binding)
    }
    override fun getItemCount() = list.size

    fun add(bindForecast: BindForecastHour) {
        if(list.size > 8){
            list.clear()
        }
        list.add(bindForecast)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    class HourViewHolder(private val binding: ItemHourBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.bindHour, obj)
            binding.executePendingBindings()
        }
    }
}