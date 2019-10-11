package com.monash.monashuniapp.Models

// API Response containing a list of Lectures for a given student
data class LectureList( var response: List<Lecture>)

// Lecture object contains start time, end time, lecture name, lecturer name and location
data class Lecture( var startTime: String, var endTime: String, var lectureTitleName: String, var lecturerName: String, var location: String )