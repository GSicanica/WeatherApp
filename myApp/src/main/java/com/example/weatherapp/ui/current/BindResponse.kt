package com.example.weatherapp.ui.current

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.weatherapp.data.model.ByLocationModel
import com.example.weatherapp.data.responseByLocation.ResponseByLocation
import com.example.weatherapp.domain.toModel
import kotlin.properties.Delegates
import com.example.weatherapp.BR
import com.example.weatherapp.utils.format

class BindResponse(val responseByCity: ResponseByLocation?) : BaseObservable() {

    var iconUrl : String
    lateinit var bindModel : ByLocationModel

    @get:Bindable
    var imageUrl  by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    init {
        val icon = responseByCity?.weather?.get(0)?.icon
        //for glide
        iconUrl = "http://openweathermap.org/img/w/$icon.png"

        if (responseByCity != null) {
            bindModel = responseByCity.toModel()
            bindModel.icon = iconUrl

            val number =(responseByCity.main?.temp?.minus(273.15))
            val celsius = number?.format(2)
            bindModel.temp = celsius.toString()
        }
        imageUrl = iconUrl
    }

}
