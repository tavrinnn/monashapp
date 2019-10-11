package com.monash.monashuniapp.Models

// API Response containing a list of buses travelling along a the path
data class BusList( var response: List<Bus>)

// Bus object contains the from, to and time for the bus
data class Bus( var from: String, var to: String, var eta: String )