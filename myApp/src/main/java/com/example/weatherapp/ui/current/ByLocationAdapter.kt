package com.example.weatherapp.ui.current

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemByLocationBinding
import javax.inject.Inject

class ByLocationAdapter @Inject constructor() :
    RecyclerView.Adapter<ByLocationAdapter.WeatherViewHolder>() {

    var list = mutableListOf<BindResponse>()
    lateinit var binding: ItemByLocationBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_by_location, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val dataModel = list[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = list.size

    fun add(newList: BindResponse) {
        list.clear()
        list.add(newList)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(private val binding: ItemByLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?) {
            binding.setVariable(BR.bindLocation, obj)
            binding.executePendingBindings()
        }
    }
}