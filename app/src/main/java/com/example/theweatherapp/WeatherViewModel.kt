package com.example.theweatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel(){

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: MutableStateFlow<WeatherResponse?> get() = _weatherData
    private val weatherApi = WeatherApi.create()


    suspend fun fetchWeather(city:String, apiKey:String){
        try {
            val response= weatherApi.getWeather(city,apiKey)
            _weatherData.value=response
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun getWeatherData(city: String, apiKey: String) {
        viewModelScope.launch {
            fetchWeather(city, apiKey)  // Call the suspend function inside a coroutine
        }
    }

}