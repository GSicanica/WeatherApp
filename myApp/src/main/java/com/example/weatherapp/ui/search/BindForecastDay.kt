package com.example.weatherapp.ui.search

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.utils.format
import com.example.weatherapp.utils.getDayOfWeek
import kotlin.properties.Delegates


class BindForecastDay(val forecastModel: ForecastModel) : BaseObservable() {

    var dayInWeek : String
    var hour : String

    @get:Bindable
    var imageUrlForecast by Delegates.observable("http://openweathermap.org/img/wn/10d@2x.png") { _, _, _ ->
        notifyPropertyChanged(
            BR.imageUrlForecast
        )
    }

    init {
        val icon = forecastModel.icon
        imageUrlForecast = "http://openweathermap.org/img/w/${icon}.png"

        forecastModel.temp = forecastModel.temp.toDouble().minus(273.15).format(2).toString()
        forecastModel.tempMin = forecastModel.tempMin.toDouble().minus(273.15).format(2).toString()
        forecastModel.tempMax = forecastModel.tempMax.toDouble().minus(273.15).format(2).toString()
        hour = forecastModel.dtTxt.subSequence(10, 16).toString()

        val date = forecastModel.dtTxt
        dayInWeek = getDayOfWeek(date)
    }
}
