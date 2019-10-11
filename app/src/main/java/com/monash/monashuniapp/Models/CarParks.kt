package com.monash.monashuniapp.Models

// API Response containing all the carpark information
data class CarParkList( var response: List<CarPark>)

// Lecture object contains start time, end time, lecture name, lecturer name and location
data class CarPark( var freeSpace: String )